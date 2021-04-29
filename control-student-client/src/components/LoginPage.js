import React, {Component} from 'react';
import '../style/login.css'
import {AxiosInstance as axios} from "axios";
import {BASE_URL, ROLE, TOKEN} from "./const/constants";
import jwtDecode from "jwt-decode";

class LoginPage extends Component {
    constructor(props) {
        super(props);
        this.state = {
            hasError: false
        }
    }
    login = () => {
        let pass=document.getElementsByName("pass");
        let login=document.getElementsByName("email");
        let data={
            username:login,
            password:pass
        }
        axios.post(BASE_URL + 'auth/login', data).then(res => {
            console.log(res, 'RES LOGIN')
            localStorage.setItem(TOKEN, res.data.type + res.data.token)
            const parsedToken = jwtDecode(res.data.token)
            localStorage.setItem(ROLE, parsedToken.roles[0].roleName)
            let roleName=parsedToken.roles[0].roleName
            if (roleName === 'ROLE_ADMIN'){
                this.props.history.push("/")
            }
            else if(roleName === 'ROLE_MANAGER'){
                this.props.history.push("/managerReport")
            }
            else if(roleName === 'ROLE_SELLER'){
                this.props.history.push("/sellerSale")
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
                                    <input type="text" name="email" placeholder="username"/>
                                    <input type="password" name="pass" placeholder="Parol"/>
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