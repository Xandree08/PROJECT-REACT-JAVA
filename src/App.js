import logo from './logo.svg';
import './App.css';
import Header from'./Header/Header'
import Nav from './Nav/nav';
import Body from './Body/body';
import Login from './Login/login';
import { AuthProvider } from './Header/AuthContext';



function App() {
  return (
    <AuthProvider>
          <div className="App">
       
       <Header/>
       <div className='body'>
       
       <Body/>
       
       </div>
       
       

      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
    </AuthProvider>

  );
}

export default App;
