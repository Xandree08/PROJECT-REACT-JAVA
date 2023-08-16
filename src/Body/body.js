import "./body.css"
import banner from "./WhatsApp Image 2023-07-25 at 13.44.50 (1).jpeg"
import banner2 from "./WhatsApp Image 2023-07-25 at 13.44.50 (2).jpeg"
import banner3 from "./WhatsApp Image 2023-07-25 at 13.44.50 (3).jpeg"
import React , {useState, useEffect} from 'react';




function Body (){

    return (
    
        <div className="body">
          <div className="body-banners">

            <img src={banner} className="banner"></img>
            <img src={banner2} className="banner"></img>
            <img src={banner3} className="banner"></img>

          </div>
          <div className="vyrus-bar">1ST DROP</div>
          <div className="body-products"></div>
        </div>
    
    )

}
export default Body