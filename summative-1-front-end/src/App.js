import './App.css';
import Consoles from './Consoles';
import Games from './Games'

// large amounts of code taken from customer-data-service class project

function App() {
  return (
    <div className="App">
      <h1>Fake Gamestop</h1>
      <Consoles />
      <Games/>
    </div>
  );
}

export default App;
