import React, {Component} from 'react';
import {Spinner} from "../../componet/Spinner";
import EmployeeModal from "../../componet/modals/EmployeeModal";
import PassportPage from "./PassportPage";

class EmployeeTable extends Component {
    constructor(props) {
        super(props);
        this.state={
            deans:[],
            districts:[],
            educations:[],
            addresses:[],
            roleNames:["ROLE_DEAN",
                "ROLE_DEPUTY_DEAN",
                "ROLE_TEACHER",
                "ROLE_STUDENT"]
        }
    }

    render() {
        return (
            <div>
                <EmployeeModal/>
                <h2 className="align-content-center">Xodimlar</h2>
                <div className="modal-footer">
                    <button className="btn btn-success"
                            // onClick={this.updateAll}
                    >
                        <i className="fas fa-cloud-download-alt"></i>
                    </button>
                    <button type="button" className="btn btn-primary" data-toggle="modal"
                            data-target=".bd-example-modal-lg">Xodim qo'shish
                    </button>
                </div>
                <table className="table">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Muassasa nomi</th>
                        <th>Manzili</th>
                    </tr>
                    </thead>
                    {
                        // this.state.isLoading?<Spinner/>:
                        //     <tbody>
                        //     {
                        //         this.state.educations.map((item)=>
                        //             <tr>
                        //                 <td>{item.id}</td>
                        //                 <td>{item.name}</td>
                        //                 <td>{item.address.district.region.country.name+", "+
                        //                 item.address.district.region.name+", "+
                        //                 item.address.district.name+", "+
                        //                 item.address.address
                        //                 }</td>
                        //             </tr>
                        //         )
                        //     }
                        //     </tbody>
                    }
                </table>
                <PassportPage/>
            </div>
        );
    }
}

export default EmployeeTable;