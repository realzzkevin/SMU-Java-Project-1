import React, { useEffect, useState } from 'react'
import TShirtCard from './TShirtCard'
import TShirtForm from './TShirtForm'


const TShirts = () => {

  const [tshirts, setTShirts] = useState([])
  const [error, setError] = useState()
  const [showForm, setShowForm] = useState(false)
  const [scopedTShirt, setScopedTShirt] = useState({})

  useEffect(() => {
    fetch("http://localhost:8080/tshirt")
      .then(response => response.json())
      .then(result => setTShirts(result))
      .catch(err => console.log(err))
  }, [])

  const fetchByColor = (event) => {
    if (event.target.value === "") {
      fetch("http://localhost:8080/tshirt")
        .then(response => response.json())
        .then(result => setTShirts(result))
        .catch(err => console.log(err))
    } else {
      fetch("http://localhost:8080/tshirt/color/" + event.target.value)
        .then(response => response.json())
        .then(result => setTShirts(result))
        .catch(error => console.log(error))
    }
  }

  const fetchBySize = (event) => {
    if (event.target.value === "") {
      fetch("http://localhost:8080/tshirt")
        .then(response => response.json())
        .then(result => setTShirts(result))
        .catch(err => console.log(err))
    } else {
      fetch("http://localhost:8080/tshirt/size/" + event.target.value)
        .then(response => response.json())
        .then(result => setTShirts(result))
        .catch(error => console.log(error))
    }
  }

  const addClick = () => {
    setScopedTShirt({ id: 0 })
    setShowForm(true)
  }

  const notify = ({ action, tshirt, error }) => {
    if(error) {
        setError(error)
        setShowForm(false)
        return
    }

    switch (action) {
        case "add" :
            setTShirts([...tshirts, tshirt])
            break
        case "edit" :
            setTShirts(tshirts.map(t => {
                if (t.id === tshirt.id) {
                    return tshirt
                }
                return t
            }))
            break
        case "edit-form" : 
            setScopedTShirt(tshirt)
            setShowForm(true)
            return 
        case "delete" :
            setTShirts(tshirts.filter(t => t.id !== tshirt.id))
            break
        case "cancel" :
            setShowForm(false)
            break
        default : 
            console.log("Not a valid action in this world!")
    }

    setError("")
    setShowForm(false)
  }

  if (showForm) {
    return <TShirtForm tshirt={scopedTShirt} notify={notify} />
  }


  return (
    <div className='container'>
      <h2>T-Shirts</h2>
      <div id='buttonPanel'>
        <button className="btn btn-primary" type='button' onClick={addClick}>Add a T-Shirt</button>
        <select name='color' className='btn btn-outline-secondary' onChange={fetchByColor}>
          <option value=''>Get T-shirts by Color</option>
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
        <select name='size' className='btn btn-outline-secondary' onChange={fetchBySize}>
          <option value=''>Get T-shirts by Size</option>
          <option value='Extra-Small'>Extra-Small</option>
          <option value='Small'>Small</option>
          <option value='Medium'>Medium</option>
          <option value='Large'>Large</option>
          <option value='Extra-Large'>Extra-Large</option>
        </select>
      </div>
      {error && <div className='alert alert-danger'>{error}</div>}
      <div>
        <table id='store-table'>
            <tr>
                <th>Color</th>
                <th>Size</th>
                <th>Description</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Actions</th>
            </tr>
            <tbody>
                {tshirts.map(tshirt => <TShirtCard key={tshirt.id} tshirt={tshirt} notify={notify} />)}
            </tbody>
        </table>
      </div>
    </div>
  )
}

export default TShirts 