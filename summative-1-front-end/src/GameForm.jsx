import React, { useState } from "react";

const GameForm = ({ game: initialGame, notify}) =>{

    const [game, setGame] = useState(initialGame)
    const isAdd = initialGame.id === 0;

    const handleChange = (event) => {
        const clone = { ...game};
        clone[event.target.name] = event.target.value;
        setGame(clone);
    }

    const handleSubmit = (event) => {
        event.preventDefault()

        const url = `http://localhost:8080/game`
        const method = isAdd ? "POST" : "PUT"
        const expectedStatus = isAdd ? 201 : 204

        const init = {
            method,
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json"
            },
            body: JSON.stringify(game)
        }

        fetch(url, init)
            .then(response => {
                if (response.status === expectedStatus) {
                    if (isAdd) {
                        return response.json()
                    } else {
                        return game
                    }
                }
                return Promise.reject(`Didn't receive expected status: ${expectedStatus}`)
            })
            .then(result => notify({
                action: isAdd ? "add" : "edit",
                game: result
            }))
            .catch(error => notify({ error: error }))
    }

    return(
        <div>
            <h1>{isAdd ? "Add": "Edit"}Game</h1>
            <form onSubmit={handleSubmit}>
                <div>
                    <label htmlFor='title'>title</label>
                    <input type='text' id='title' name='title' value={game.title} onChange={handleChange} />
                </div>
                <div>
                    <label htmlFor='description'>description</label>
                    <input type='text' id='description' name='description' value={game.description} onChange={handleChange} />
                </div>
                <div>
                    <label htmlFor='studio'>studio</label>
                    <input type='text' id='studio' name='studio' value={game.studio} onChange={handleChange} />
                </div>
                <div>
                    <label htmlFor='esrbrating'>ESRB</label>
                    <select id="esrbrating" name='esrbrating' value={game.esrbrating} onChange={handleChange}>
                        <option value="">choose a ESRB rating</option>
                        <option value='EVERYONE'>EVERYONE</option>
                        <option value='EVERYONE 10+'>EVERYONE 10+</option>
                        <option value='TEEN'>TEEN</option>
                        <option value="MATURE 17+">MATURE 17+</option>
                        <option value="ADULTS ONLY 18+">ADULTS ONLY 18+</option>
                    </select>
                </div>
                <div>
                    <label htmlFor='price'>Price</label>
                    <input type='number' id='price' name='price' value={game.price} onChange={handleChange} />
                </div>
                <div>
                    <label htmlFor='quantity'>Quantity</label>
                    <input type='number' id='quantity' name='quantity' value={game.quantity} onChange={handleChange} />
                </div>
                <div>
                    <button type='submit'>Save</button>
                    <button onClick={() => notify({ action: "cancel" })}>Cancel</button>
                </div>
            </form>


        </div>
    )


}

export default GameForm