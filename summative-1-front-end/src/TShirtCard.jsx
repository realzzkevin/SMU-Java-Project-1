import React from 'react'

const TShirtCard = ({ tshirt, notify }) => {

    const handleDelete = () => {
        fetch(`http://localhost:8080/tshirt/${tshirt.id}`, { method: "DELETE" })
            .then(() => notify({ action: "delete", tshirt: tshirt}))
            .catch(error => notify({ action: "delete", error: error}))
    }

    return (
        <tr key= {tshirt.id}>
            <td>{tshirt.color}</td>
            <td>{tshirt.size}</td>
            <td>{tshirt.description}</td>
            <td>{tshirt.price}</td>
            <td>{tshirt.quantity}</td>
            <td>
                <button onClick={handleDelete}>Delete</button>
                <button onClick={() => notify({ action: "edit-form", tshirt: tshirt })}>Edit</button>
            </td>
        </tr>
    )
}

export default TShirtCard