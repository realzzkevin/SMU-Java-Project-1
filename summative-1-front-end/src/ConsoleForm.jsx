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
                        return console
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
        <div id='form-container' className='container'>
            <h2>{isAdd ? "Add" : "Edit"} Console</h2>
            <form onSubmit={handleSubmit}>
                <div className='mb-3'>
                    <label htmlFor='model'>Model</label>
                    <input type='text' id='model' name='model' className='form-control' value={console.model} onChange={handleChange} />
                </div>
                <div className='mb-3'>
                    <label htmlFor='manufacturer'>Manufacturer</label>
                    <select name='manufacturer' className='form-control' value={console.manufacturer} onChange={handleChange} >
                        <option value='Nintendo'>Nintendo</option>
                        <option value='Sony'>Sony</option>
                        <option value='Microsoft'>Microsoft</option>
                    </select>
                </div>
                <div className='mb-3'>
                    <label htmlFor='memory'>Memory Amount</label>
                    <input type='text' id='memory' name='memory' className='form-control' value={console.memory_amount} onChange={handleChange} />
                </div>
                <div className='mb-3'>
                    <label htmlFor='processor'>Processor</label>
                    <input type='text' id='processor' name='processor' className='form-control' value={console.processor} onChange={handleChange} />
                </div>
                <div className='mb-3'>
                    <label htmlFor='price'>Price</label>
                    <input type='number' id='price' name='price' className='form-control' value={console.price} onChange={handleChange} />
                </div>
                <div className='mb-3'>
                    <label htmlFor='quantity'>Quantity</label>
                    <input type='number' id='quantity' name='quantity' className='form-control' value={console.quantity} onChange={handleChange} />
                </div>
                <div className='mb-3'>
                    <button className='btn btn-primary' type='submit'>Save</button>
                    <button className='btn btn-secondary' onClick={() => notify({ action: "cancel" })}>Cancel</button>
                </div>
            </form>
        </div>
    )
}

export default ConsoleForm