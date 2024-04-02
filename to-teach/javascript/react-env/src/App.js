import './App.css';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <p>
	  {process.env.REACT_APP_TITLE}
	</p>
        <p>
	  {process.env.REACT_APP_TEXT}
        </p>
      </header>
    </div>
  );
}

export default App;
