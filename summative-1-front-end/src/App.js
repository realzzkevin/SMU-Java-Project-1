import './App.css';
import TShirts from './TShirt';
import Consoles from './Consoles';

// large amounts of code taken from customer-data-service class project

function App() {
  return (
    <div className="App">
      <h1>Fake Gamestop</h1>
      <Consoles />
      <TShirts />
    </div>
  );
}

export default App;
