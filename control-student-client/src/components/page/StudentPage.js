import React, {Component} from 'react';
import {Spinner} from "../componet/Spinner";
import NavbarComponent from "../componet/Navbar";

class StudentPage extends Component {
    constructor(props) {
        super(props);
        this.state={
            teachers:[],
            students:[],
            reports:[],
            groups:[],
            directions:[],
            isLoading:true
        }
    }

    componentDidMount() {
        let a=true;
        setTimeout(()=>{
            this.setState({
                student:[],
                isLoading: false
            })
        }, 3000);

    }

    render() {
        return (
            <div>
                {this.state.isLoading?<Spinner/>:<div>
                    <NavbarComponent/>
                    Student Page


                </div>}
            </div>
        );
    }
}

export default StudentPage;