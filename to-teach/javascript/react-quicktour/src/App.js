import './App.css';
import React, { useState, useEffect  } from 'react';
import Products from './products'

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <Products />
      </header>
    </div>
  );
}

export default App;
