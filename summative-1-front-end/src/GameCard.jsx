import React from "react";

const GameCard = ( { game, notify} ) => {
    const handleDelete = () => {
        fetch(`http://localhost:8080/game/${game.id}`, { method: "DELETE" })
            .then(() => notify({ action: "delete", game: game }))
            .catch(error => notify({ action: "delete", error: error }))
    }

    return (
        <tr key={game.id}>
            <td>{game.title}</td>
            <td>{game.description}</td>
            <td>{game.studio}</td>
            <td>{game.esrbrating}</td>
            <td>{game.price}</td>
            <td>{game.quantity}</td>
            <td>
                    <button onClick={handleDelete}>Delete</button>
                    <button onClick={() => notify({ action: "edit-form", game: game })}>Edit</button>
            </td>
        </tr>

    )

}

export default GameCard