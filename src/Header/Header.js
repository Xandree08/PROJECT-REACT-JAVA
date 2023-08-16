import './header.css'
import icon from './bars-solid.svg'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBars, faCar, faWeight } from '@fortawesome/free-solid-svg-icons';
import { faUser } from '@fortawesome/free-solid-svg-icons';
import { faMagnifyingGlass } from '@fortawesome/free-solid-svg-icons';
import { faCartShopping } from '@fortawesome/free-solid-svg-icons';

import Login  from './Login/login'

import Body from '../Body/body';
import { useState } from 'react';
import { useAuth } from './AuthContext';





function Header(){
   
    const {setFormUser ,FormUser} = useAuth();
    const {token}=useAuth();



    const [authVisible , setAuthVisible] = useState(false)

    const authentication = ()=>{
        setAuthVisible(!authVisible);
    }

  

   
    



return(
  
    <div className="Container-Header">
     
        
        <nav className="top-header">
        <a className='top-header-a'>Utilze os cupoms de descontos de 10% a 60% off / Fique por dentro das promoções.</a>
        </nav>
        <div className="content-header">
            <div className='content-bar'>
            <FontAwesomeIcon icon={faBars} style={{color: "#f900ed",}} className='icon-bar' />
            
            </div>
            <div className='content-logo'>
            <a>VYRUS</a>
            </div>
            <div className='content-icons'>
            <a>{FormUser.username === "" ? "":FormUser.username }</a>
            <FontAwesomeIcon icon={faMagnifyingGlass} style={{color: "#f900ed",}} className='icons-1' />
            <FontAwesomeIcon icon={faUser} style={{color: "#f900ed",}} className='icons-2' onClick={authentication}/>
            <FontAwesomeIcon icon={faCartShopping} style={{color: "#f900ed",}}className='icons-3' />
            <label style={{color: "#f900ed",}}>R$0,00 </label>
            </div>
            
        </div>
        {authVisible && <div className='auth'><Login/></div>}
      
        
    </div>
    
   

);

}

export default Header ;

