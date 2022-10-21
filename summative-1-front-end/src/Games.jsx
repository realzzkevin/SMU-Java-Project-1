import React, { useEffect, useState} from "react"
import GameCard from './GameCard'
import GameForm from "./GameForm"

const Games = () => {

    const [games, setGames] = useState([])
    const [error, setError] = useState()
    const [showForm, setShowForm] = useState(false)
    const [ScopeGame, setScopeGame] = useState({})

    useEffect(() => {
        fetchAll();
    }, [])

    const fetchAll = () => {

        fetch("http://localhost:8080/game/")
        .then(response => response.json())
        .then(result => setGames(result))
        .catch(error => console.log(error))
    }

    const fetchByStudio = (event) => {
        if(event.target.value === ""){
                fetchAll();
        } else{
            fetch("http://localhost:8080/game/studio/" + event.target.value)
            .then(response => response.json())
            .then(result => {
                if(result.length == 0){
                    setGames(games);
                } else {
                    setGames(result);
                }
            })
            .catch(error => console.log(error))
        }
    }

    const fetchByESRB = (event) => {
        if (event.target.value === "") {
            fetchAll();
          } else {
            fetch("http://localhost:8080/game/esrb/" + event.target.value)
              .then(response => response.json())
              .then(result => setGames(result))
              .catch(error => console.log(error))
          }

    }

    const fetchByTitle = (event) => {
        if (event.target.value === "") {
            fetchAll();
          } else {
            fetch("http://localhost:8080/game/title/" + event.target.value)
              .then(response => response.json())
              .then(result => {
                if(result.length == 0){
                    setGames(games)
                } else {
                    setGames(result)
                }
              })
              .catch(error => console.log(error))
          }

    }

    const addClick = () => {
        setScopeGame({ id : 0})
        setShowForm(true)
    }

    const notify = ({ action, game, error }) => {
        if (error) {
          setError(error)
          setShowForm(false)
          return
        }
    
        switch (action) {
          case "add":
            setGames([...games, game])
            break
          case "edit":
            setGames(games.map(g => {
              if (g.id === game.id) {
                return game
              }
              return g
            }))
            break
          case "edit-form":
            setScopeGame(game)
            setShowForm(true)
            return
          case "delete":
            setGames(games.filter(g => g.id !== game.id))
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
        return <GameForm game={ScopeGame} notify={notify} />
      }
    

    return (
        <div className="container">
            <h2>Games</h2>
            <div id="buttonPanel">
                <button className='btn btn-primary' type="button" onClick={addClick}>
                    Add a Game
                </button>

                <select name='studio' className='btn btn-outline-secondary' onChange={fetchByESRB}>
                    <option value=''>All ESRB</option>
                    <option value='EVERYONE'>EVERYONE</option>
                    <option value='EVERYONE 10+'>EVERYONE 10+</option>
                    <option value='TEEN'>TEEN</option>
                    <option value="MATURE 17+">MATURE 17+</option>
                    <option value="ADULTS ONLY 18+">ADULTS ONLY 18+</option>
                </select>

                <form className='btn btn-outline-secondary' >
                    <label htmlFor="search-by-studio"  className="btn btn-primary">Search By Studio:</label>
                    <input type="search" id="search-by-studio"  onChange={fetchByStudio}/>
                </form>
                
                <form className='btn btn-outline-secondary'>
                    <label htmlFor="search-by-title" className ='btn btn-primary'>Search By Title :</label>
                    <input type="search" id="search-by-title" name ="search-by-title" onChange={fetchByTitle}/>
                </form>
            </div>
            {error && <div className='alert alert-danger'>{error}</div>}
            <div>
                <table id="store-table">
                    <tr>
                        <th>Title</th>
                        <th>Description</th>
                        <th>Studio</th>
                        <th>ESRB</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Actions</th>
                    </tr>
                    <tbody>
                    {games.map(game => <GameCard key={game.id} game={game} notify={notify} />)}
                    </tbody>
                </table>
            </div>

        </div>
    )

}

export default Games