import React, {Component} from 'react';
import '../style/login.css'
import axios from "axios";
import {BASE_URL, LOGIN_TIME, ROLE, TOKEN} from "./const/constants";
import jwtDecode from "jwt-decode";

class LoginPage extends Component {
    constructor(props) {
        super(props);
        this.state = {
            hasError: false
        }
    }
    login = () => {
        const pass=document.getElementById("password").value;
        const login=document.getElementById("login").value;
        let data={
            username:login,
            password:pass
        };
        console.log(data)
            axios.post(BASE_URL+'auth/login',data)
            .then(res => {
            console.log(res, 'RES LOGIN');
            localStorage.setItem(TOKEN, res.data.type + res.data.token);
            const parsedToken = jwtDecode(res.data.token)
            localStorage.setItem(ROLE, parsedToken.roles[0].roleName);
            localStorage.setItem(LOGIN_TIME,new Date().getTime().toString());
            let roleName=parsedToken.roles[0].roleName;
            if (roleName === 'ROLE_ADMIN'){
                this.props.history.push("/")
            }
            else if(roleName === 'ROLE_DEAN'){
                this.props.history.push("/dean")
            }
            else if(roleName === 'ROLE_DEPUTY_DEAN'){
                this.props.history.push("/deputyDean")
            }
            else if(roleName === 'ROLE_STUDENT'){
                this.props.history.push("/student")
            }else if(roleName === 'ROLE_TEACHER'){
                this.props.history.push("/teacher")
            }else {
                this.props.history.push("/login")
            }

        })
    };
    style={
        height:window.innerHeight+"px"
    };
    render() {
        return (
            <div>
                <div className="container-fluid container1" style={this.style}>
                    <div className="container-fluid con-shad" style={this.style}>

                            <div id="msform">
                                <fieldset>
                                    <h2 className="fs-title">KIRISH</h2>
                                    <input type="text" id="login" name="email" placeholder="username"/>
                                    <input type="password" id="password" name="pass" placeholder="Parol"/>
                                    <label className="form-remember"><input type="checkbox"/></label>
                                    <input type="button" name="next" className="next action-button" onClick={this.login} value="Kirish"/>
                                </fieldset>
                            </div>

                    </div>
                </div>
            </div>
        );
    }
}


export default LoginPage;