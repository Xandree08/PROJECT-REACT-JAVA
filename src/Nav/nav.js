import './nav.css'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faShirt } from '@fortawesome/free-solid-svg-icons';
import { faPercent } from '@fortawesome/free-solid-svg-icons';

function Nav (){

return (
    
    <div className="nav-list">
        <div className="nav-list-logo">

            <label> PRODUTOS</label>
              
        </div>
        <div className="nav-list-produtos">
            <label className='male'>MASCULINOS</label>
            <a href="PROMOCOES">Promoções <FontAwesomeIcon icon={faPercent} style={{color: "#f900ed",}} className='icons-2' /> </a>
            <a href="CAMISAS">Camisetas <FontAwesomeIcon icon={faShirt} style={{color: "#f900ed",}} className='icons' /></a>
            
            <label className='female'>FEMININAS</label>
            <a href="">Promoções <FontAwesomeIcon icon={faPercent} style={{color: "#f900ed",}} className='icons-2' /></a>
            <a href="">Camisetas <FontAwesomeIcon icon={faShirt} style={{color: "#f900ed",}} className='icons' /></a >
        

            

        </div>


    </div>
    


)

}
export default Nav;