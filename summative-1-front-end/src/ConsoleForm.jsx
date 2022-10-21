import React, { useState } from 'react'

const ConsoleForm = ({ console: initialConsole, notify }) => {

    const [console, setConsole] = useState(initialConsole)
    const isAdd = initialConsole.id === 0

    const handleChange = (event) => {
        const clone = {...console}
        clone[event.target.name] = event.target.value
        setConsole(clone)
    }

    const handleSubmit = (event) => {
        event.preventDefault()
    

        const url = `http://localhost:8080/console`
        const method = isAdd ? "POST" : "PUT"
        const expectedStatus = isAdd ? 201 : 204

        const init = {
            method,
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json"
            },
            body: JSON.stringify(console)
        }

        fetch(url, init)
            .then(response => {
                if (response.status === expectedStatus) {
                    if (isAdd) {
                        return response.json()
                    } else {
                        return console;
                    }
                }
                return Promise.reject(`Didn't receive expected status: ${expectedStatus}`)
            })
            .then(result => notify({
                action: isAdd ? "add" : "edit",
                console: result
            }))
            .catch(error => notify({ error: error }))
    }

    return (
        <div>
            <h1>{isAdd ? "Add" : "Edit"} Console</h1>
            <form onSubmit={handleSubmit}>
                <div>
                    <label htmlFor='model'>Model</label>
                    <input type='text' id='model' name='model' value={console.model} onChange={handleChange} />
                </div>
                <div>
                    <label htmlFor='manufacturer'>Manufacturer</label>
                    <select name='manufacturer' value={console.manufacturer} onChange={handleChange} >
                        <option value="">Choose a manufacturer</option>
                        <option value='Nintendo'>Nintendo</option>
                        <option value='Sony'>Sony</option>
                        <option value='Microsoft'>Microsoft</option>
                    </select>
                </div>
                <div>
                    <label htmlFor='memoryAmount'>Memory Amount</label>
                    <input type='text' id='memoryAmount' name='memoryAmount' value={console.memoryAmount} onChange={handleChange} />
                </div>
                <div>
                    <label htmlFor='processor'>Processor</label>
                    <input type='text' id='processor' name='processor' value={console.processor} onChange={handleChange} />
                </div>
                <div>
                    <label htmlFor='price'>Price</label>
                    <input type='number' id='price' name='price' value={console.price} onChange={handleChange} />
                </div>
                <div>
                    <label htmlFor='quantity'>Quantity</label>
                    <input type='number' id='quantity' name='quantity' value={console.quantity} onChange={handleChange} />
                </div>
                <div>
                    <button type='submit'>Save</button>
                    <button onClick={() => notify({ action: "cancel" })}>Cancel</button>
                </div>
            </form>
        </div>
    )
}

export default ConsoleForm