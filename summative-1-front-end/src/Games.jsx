import React, { useEffect, useState} from "react"
import GameCard from './GameCard'
import GameForm from "./GameForm"

const Games = () => {

    const [games, setGames] = useState([])
    const [error, setError] = useState()
    const [showForm, setShowForm] = useState(false)
    const [ScopeGame, setScopeGame] = useState({})

    // useEffect(() => {
    //     fetch("http://localhost:8080/game")
    //     .then(response => response.json())
    //     .then(result => setGames(result))
    //     .catch(err => console.log(err))        
    // }, [])
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
        <div>
            <div>
                <button type="button" onClick={addClick}>
                    Add a Game
                </button>

                <select name='studio' onChange={fetchByESRB}>
                    <option value=''>All ESRB</option>
                    <option value='EVERYONE'>EVERYONE</option>
                    <option value='EVERYONE 10+'>EVERYONE 10+</option>
                    <option value='TEEN'>TEEN</option>
                    <option value="MATURE 17+">MATURE 17+</option>
                    <option value="ADULTS ONLY 18+">ADULTS ONLY 18+</option>
                </select>

                <form >
                    <label htmlFor="search-by-studio">Search By studio:</label>
                    <input type="search" id="search-by-studio" name ="search-by-studio" onChange={fetchByStudio}/>
                </form>
                
                <form>
                    <label htmlFor="search-by-title">Search By Title :</label>
                    <input type="search" id="search-by-title" name ="search-by-title" onChange={fetchByTitle}/>
                </form>
            </div>

            <div>
                <h1>
                    Games
                </h1>
                <table>
                    <tr>
                        <th>title</th>
                        <th>Description</th>
                        <th>Studio</th>
                        <th>ESRB</th>
                        <th>Price</th>
                        <th>Quantity</th>
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