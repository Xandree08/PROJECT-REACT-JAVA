import { useContext, createContext , useState , useEffect } from "react";

const AuthContext = createContext();

export function AuthProvider ({children}){

    const [token, setToken] = useState(localStorage.getItem('token') || '');
    const [FormUser, setFormUser] = useState([]);

    useEffect(() => {
        localStorage.setItem('token', token);
      }, [token]);


    return (
        <AuthContext.Provider value={{token,FormUser,setToken,setFormUser}}>
  
 
       {children}

        </AuthContext.Provider>

    );

}

export function useAuth (){
        return useContext(AuthContext);
}

