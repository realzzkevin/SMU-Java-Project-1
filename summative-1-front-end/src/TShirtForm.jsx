import React, {useState} from 'react';

const TShirtForm = ({ tshirt: initialTShirt, notify}) => {

    const [tshirt, setTShirt] = useState(initialTShirt)
    const isAdd = initialTShirt.id === 0

    const handleChange = (event) => {
        const clone = {...tshirt}
        clone[event.target.name] = event.target.value 
        setTShirt(clone)
    }

    const handleSubmit = (event) => {
        event.preventDefault()

        const url = `http://localhost:8080/tshirt`
        const method = isAdd ? "POST" : "PUT"
        const expectedStatus = isAdd ? 201 : 204

        const init = {
            method,
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json"
            },
            body: JSON.stringify(tshirt)
        }

        fetch(url, init)
            .then(response => {
                if (response.status === expectedStatus) {
                    if (isAdd) {
                        return response.json()
                    } else {
                        return tshirt
                    }
                }
                return Promise.reject(`Didn't receive expected status: ${expectedStatus}`)
            })
            .then(result => notify({
                action: isAdd ? "add" : "edit",
                tshirt: result
            }))
            .catch(error => notify({ error: error }))
    }

    return (
        <div>
            <h1>{isAdd ? "Add" : "Edit"} TShirt</h1>
            <form onSubmit={handleSubmit}>
                <div>
                    <label htmlFor='color'>Color</label>
                    <select name='color' value={tshirt.color} onChange={handleChange} >
                        <option value='Red'>Red</option>
                        <option value='Blue'>Blue</option>
                        <option value='Green'>Green</option>
                        <option value='Yellow'>Yellow</option>
                        <option value='Purple'>Purple</option>
                        <option value='Aqua'>Aqua</option>
                        <option value='Black'>Black</option>
                        <option value='Gray'>Gray</option>
                        <option value='Maroon'>Maroon</option>
                        <option value='Navy'>Navy</option>
                        <option value='Olive'>Olive</option>
                        <option value='Silver'>Silver</option>
                        <option value='White'>White</option>
                        <option value='Teal'>Teal</option>
                    </select>
                    {/* <input type='text' id='color' name='color' value={tshirt.color} onChange={handleChange} /> */}
                </div>
                <div>
                    <label htmlFor='size'>Size</label>
                    <select name='size' value={tshirt.size} onChange={handleChange} >
                        <option value='Extra-Small'>Extra-Small</option>
                        <option value='Small'>Small</option>
                        <option value='Medium'>Medium</option>
                        <option value='Large'>Large</option>
                        <option value='Extra-Large'>Extra-Large</option>
                    </select>
                </div>
                <div>
                    <label htmlFor='description'>Description</label>
                    <input type='text' id='description' name='description' value={tshirt.description} onChange={handleChange} />
                </div>
                <div>
                    <label htmlFor='price'>Price</label>
                    <input type='number' id='price' name='price' value={tshirt.price} onChange={handleChange} />
                </div>
                <div>
                    <label htmlFor='quantity'>Quantity</label>
                    <input type='number' id='quantity' name='quantity' value={tshirt.quantity} onChange={handleChange} />
                </div>
                <div>
                    <button type='submit'>Save</button>
                    <button onClick={() => notify({action: "cancel" })}>Cancel</button>
                </div>
            </form>
        </div>
    )
}

export default TShirtForm