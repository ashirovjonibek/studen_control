import React, {Component} from 'react';
import NavbarComponent from "../componet/Navbar";
import {Spinner} from "../componet/Spinner";
import LocationModal from "../componet/modals/LocationModal";
import LocationTable from "./inPage/LocationTable";
import EducationTable from "./inPage/EducationTable";
import EmployeeTable from "./inPage/EmployeeTable";

class AdminPage extends Component {
    constructor(props) {
        super(props);
        this.state={
            educations:[],
            faculties:[],
            isLoading:true,
            locationTable:false,
            educationTable:false,
            employeeTable:true
        }
    }

    componentDidMount() {


            this.setState({
                isLoading: false
            })


    }

    render() {
        return (
            <div className="container-fluid bg-light">
                {this.state.isLoading?<Spinner/>:
                    <div className="container">
                        <nav className="navbar navbar-expand-lg navbar-light bg-light">
                            <a className="navbar-brand">Logotip</a>
                            <div className=" navbar-collapse" id="navbarNav">
                                <ul className="navbar-nav">
                                    <li className={this.state.locationTable?"active nav-item":"nav-item"}>
                                        <a className="nav-link" href="#" onClick={
                                            ()=>this.setState({
                                                locationTable:true,
                                                educationTable:false,
                                                employeeTable:false
                                            })
                                        }>Manzillar </a>
                                    </li>
                                    <li className={this.state.educationTable?"active nav-item":"nav-item"}>
                                        <a className="nav-link" href="#" onClick={
                                            ()=>this.setState({
                                                locationTable:false,
                                                educationTable:true,
                                                employeeTable:false
                                            })
                                        }>Tlim muassasalari</a>
                                    </li>
                                    <li className={this.state.employeeTable?"active nav-item":"nav-item"}>
                                        <a className="nav-link" href="#" onClick={
                                            ()=>this.setState({
                                                locationTable:false,
                                                educationTable:false,
                                                employeeTable:true
                                            })
                                        }>Xodimlar</a>
                                    </li>
                                </ul>
                            </div>
                        </nav>


                    {this.state.locationTable?<LocationTable/>:""}
                    {this.state.educationTable?<EducationTable/>:""}
                    {this.state.employeeTable?<EmployeeTable/>:""}



                </div>}
            </div>
        );
    }
}

export default AdminPage;