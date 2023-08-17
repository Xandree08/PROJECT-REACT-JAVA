import './header.css'
import icon from './bars-solid.svg'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBars, faCar, faWeight } from '@fortawesome/free-solid-svg-icons';
import { faUser } from '@fortawesome/free-solid-svg-icons';
import { faMagnifyingGlass } from '@fortawesome/free-solid-svg-icons';
import { faRightFromBracket } from '@fortawesome/free-solid-svg-icons';

import Login from '../Login/login'

import Body from '../Body/body';
import { useState, useEffect} from 'react';
import { useAuth } from './AuthContext';





function Header() {
    const [controlFetch, setControlFetch] = useState(false);
    const { setFormUser, FormUser } = useAuth();
    const { setToken, token } = useAuth();
    const [Username ,setUsername]= useState("");
    const [Balance, setBalance]=useState("");
    const LocalUser= localStorage.getItem("FormUser");
    const [authVisible, setAuthVisible] = useState(false)
    

   
    useEffect(() => {
        if (LocalUser) {
          const FormUserJSON = JSON.parse(LocalUser);
          setUsername(FormUserJSON.username);
          setBalance(FormUserJSON.balance);
        }
      }, []);


   

    const authentication = () => {
        setAuthVisible(!authVisible);

    }

    const logout = () => {
        
        localStorage.removeItem("FormUser")
        setUsername("");
        setBalance("0");
        window.location.reload(true);
        
        
       
        
        
    }

    const fetchData = async () => {
        if (controlFetch) {
            return;
        }
    }

      
        
       
       
       
        
    
    
            return (

                <div className="Container-Header">


                    <nav className="top-header">
                        <a className='top-header-a'>Utilze os cupoms de descontos de 10% a 60% off / Fique por dentro das promoções.</a>
                    </nav>
                    <div className="content-header">
                        <div className='content-bar'>
                            <FontAwesomeIcon icon={faBars} style={{ color: "#f900ed", }} className='icon-bar' />

                        </div>
                        <div className='content-logo'>
                            <a>VYRUS</a>
                        </div>
                        <div className='content-icons'>
                            <a>{Username}</a>
                            <FontAwesomeIcon icon={faMagnifyingGlass} style={{ color: "#f900ed", }} className='icons-1' />
                            <FontAwesomeIcon icon={faUser} style={{ color: "#f900ed", }} className='icons-2' onClick={authentication} />
                            <FontAwesomeIcon icon={faRightFromBracket} style={{ color: "#f900ed", }} className='icons-3' onClick={logout} />
                            <label style={{ color: "#f900ed", }}>R${Balance} </label>
                        </div>

                    </div>
                    {authVisible && <div className='auth'><Login /></div>}


                </div>



            );

        }
    

    export default Header;

