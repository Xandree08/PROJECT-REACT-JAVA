import { json } from 'react-router-dom';
import './login.css'
import React, { useState } from 'react'
import { hasFormSubmit } from '@testing-library/user-event/dist/utils';
import Header from '../Header/Header';
import { createContext, useContext } from 'react';
import { useEffect } from 'react';
import { useAuth } from '../Header/AuthContext';



  
function Login() {



    const {token, setToken}= useAuth();
    const{FormUser, setFormUser}= useAuth();


    const [loginUser, setLoginUser] = useState("");
    const [loginPassword, setLoginPassword] = useState("")

    const [register, setRegister] = useState("")
    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");

    const [validUser, setValidUser] = useState(false);
    const [validEmail, setValidEmail] = useState(false);
    const [validPassword, setValidPassword] = useState(false);
    const [validConfirmPassword, setValidConfirmPassword] = useState(false);


    const ValidationUsername = (event) => {
        const newUsername = event.target.value
        setUsername(newUsername);
        const usernameRegex = /^[a-zA-Z0-9]{4,}$/;
        setValidUser(usernameRegex.test(newUsername));
    }

    const ValidationEmail = (event) => {

        const newEmail = event.target.value;
        setEmail(newEmail)
        const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
        setValidEmail(emailRegex.test(newEmail));


    }

    const ValidationPassword = (event) => {
        const newPassword = event.target.value;
        setPassword(newPassword)
        const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/;
        setValidPassword(passwordRegex.test(newPassword));
    }

    const ValidationConfirmPassword = (event) => {
        const newConfirmPassword = event.target.value;
        setConfirmPassword(newConfirmPassword);
        setValidConfirmPassword(password == newConfirmPassword)

    }

     
    const formregister = (event) => {
        event.preventDefault()
        if (validEmail && validPassword && validUser && validConfirmPassword) {
            const register = document.querySelector('.form-register');
            const registerdata = {
                emails: email,
                usernames: username,
                passwords: password,

            };
            fetch('http://localhost:8080/auth/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json; charset=UTF-8'
                },
                body: JSON.stringify(registerdata)
            })

            alert("Você foi cadastrado com sucesso!!");

            setUsername("")
            setEmail("")
            setPassword("")
            setConfirmPassword("")

        } else {
            alert("Erro ao cadastrar. Cheque se o usuario é repetido ou email cadastrado")
        }

    }


    const Formlogins = async (event) => {

        event.preventDefault();

        const datalogin = {
            email: loginUser,
            password: loginPassword
        }
        
        try{

            const response = await fetch('http://localhost:8080/auth/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json; charset=UTF-8'
                },
                body: JSON.stringify(datalogin)
            });

            if (response.ok) {
                const data = await response.text();
                setToken(data)
                alert("Logado com sucesso")
                const Dashboard = async () => {
                    const DataUser = await fetch('http://localhost:8080/dashboard', {
                    method: 'GET',
                    headers: {
                    'Authorization': `Bearer ${data}`
                    }
                    })
                       if(token){
                       const saveDataUser = await DataUser.json();
                       localStorage.setItem("FormUser",JSON.stringify(saveDataUser));
                       setFormUser(saveDataUser);  
                       window.location.reload(true);
                     }
                                
                 }
                 Dashboard();


                setLoginPassword("")
                setLoginUser("")
                
                
                
           }else(
            alert("Cheque suas credenciais , senha ou email inválidos !")
           )
        }catch(error){

            alert("Erro na requisição da API ")

        }
        }
    






    return (
        <div className="container-authentication">
            <div className="authentication-login">
                <form className="form-login" onSubmit={Formlogins}>
                    <h4>ENTRAR</h4>

                    <input type="text" placeholder='Email' value={loginUser} onChange={e => setLoginUser(e.target.value)}  ></input>

                    <input type="password" placeholder='Password' value={loginPassword} onChange={e => setLoginPassword(e.target.value)}  ></input>

                    <button>LOGIN</button>

                </form>
            </div>
            <div className="authentication-register">
                <form onSubmit={formregister} value={register} className="form-register">
                    <h4>CADASTRE-SE</h4>

                    <input type="text" placeholder='Username' value={username} onChange={ValidationUsername}></input>
                    {!validUser && <p className='error-text'>Digite um usário valido.</p>}

                    <input type="email" placeholder='Email' value={email} onChange={ValidationEmail}></input>
                    {!validEmail && <p className='error-text'>Digite um email valido.</p>}

                    <input type="password" placeholder='Passowrd' value={password} onChange={ValidationPassword}></input>
                    {!validPassword && <p className='error-text'>Senha fraca(M)(1,2,3,4,5,6,7,8,9)</p>}

                    <input type="password" placeholder='Confirm Password' value={confirmPassword} onChange={ValidationConfirmPassword}></input>
                    {!validConfirmPassword && <p className='error-text'>Senhas não conferem.</p>}
                    <div className='text'>
                        <a>Seus dados pessoais serão utilizados para auxiliar sua experiência de compra na loja virtual, gerenciar seu acesso a sua conta e para outros propósitos descritos em nossa políticas de privacidade. Ao se cadastrar, o usuário está de acordo com os Termos e Condições.</a>
                    </div>

                    <button type='submit'>CADASTRAR</button>
                </form>
            </div>

        </div>
    )
}



export default Login;