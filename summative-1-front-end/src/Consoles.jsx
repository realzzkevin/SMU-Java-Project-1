import React, { useEffect, useState } from 'react'
import ConsoleCard from './ConsoleCard'
import ConsoleForm from './ConsoleForm'

const Consoles = () => {

  const [consoles, setConsoles] = useState([])
  const [error, setError] = useState()
  const [showForm, setShowForm] = useState(false)
  const [scopedConsole, setScopedConsole] = useState({})

  useEffect(() => {
    fetch("http://localhost:8080/console")
      .then(response => response.json())
      .then(result => setConsoles(result))
      .catch(err => console.log(err))
  }, [])

  const fetchByManufacturer = (event) => {
    if (event.target.value === '') {
      fetch("http://localhost:8080/console")
        .then(response => response.json())
        .then(result => setConsoles(result))
        .catch(err => console.log(err))
    } else {
      fetch("http://localhost:8080/console/manufacturer/" + event.target.value)
        .then(response => response.json())
        .then(result => setConsoles(result))
        .catch(error => console.log(error))
    }
  }

  const addClick = () => {
    setScopedConsole({ id: 0 })
    setShowForm(true)
  }

  const notify = ({ action, console, error }) => {
    if (error) {
      setError(error)
      setShowForm(false)
      return
    }

    switch (action) {
      case "add":
        setConsoles([...consoles, console])
        break
      case "edit":
        setConsoles(consoles.map(c => {
          if (c.id === console.id) {
            return console
          }
          return c
        }))
        break
      case "edit-form":
        setScopedConsole(console)
        setShowForm(true)
        return
      case "delete":
        setConsoles(consoles.filter(c => c.id !== console.id))
        break
      case "cancel":
        setShowForm(false)
        break
      default:
        console.log("Not a vaild action!!")
    }

    setError("")
    setShowForm(false)
  }

  if (showForm) {
    return <ConsoleForm console={scopedConsole} notify={notify} />
  }

  return (
    <div className='container'>
      <h2>Consoles</h2>
      <div id='buttonPanel'>
        <button className='btn btn-primary' type='button' onClick={addClick}>Add a Console</button>
        <select name='manufacturer' className='btn btn-outline-secondary' onChange={fetchByManufacturer}>
          <option value=''>Get Consoles by Manufacturer</option>
          <option value='Nintendo'>Nintendo</option>
          <option value='Sony'>Sony</option>
          <option value='Microsoft'>Microsoft</option>
        </select>
      </div>
      {error && <div className='alert alert-danger'>{error}</div>}
      <div>
        <table id='store-table'>
          <tr>
            <th>Model</th>
            <th>Manufacturer</th>
            <th>Memory Amount</th>
            <th>Processor</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Actions</th>
          </tr>
          <tbody>
            {consoles.map(console => <ConsoleCard key={console.id} console={console} notify={notify} />)}
          </tbody>
        </table>
      </div>
    </div>
  )
}

export default Consoles