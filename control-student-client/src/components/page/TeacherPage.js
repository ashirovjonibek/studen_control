import React, {Component} from 'react';
import {Spinner} from "../componet/Spinner";
import NavbarComponent from "../componet/Navbar";

class TeacherPage extends Component {
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
                groups:[],
                students:[],
                raports:[],
                isLoading: false
            })
        }, 3000);

    }

    render() {
        return (
            <div>
                {this.state.isLoading?<Spinner/>:<div>
                    <NavbarComponent/>
                    Teacher Page


                </div>}
            </div>
        );
    }
}

export default TeacherPage;