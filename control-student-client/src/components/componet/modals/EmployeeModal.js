import React, {Component} from 'react';
import {Spinner} from "../Spinner";
import $ from "jquery";
import axios from "axios";
import {BASE_URL} from "../../const/constants";

class EmployeeModal extends Component {
    constructor(props) {
        super(props);
        this.state={
            countries:[],
            regions:[],
            districts:[],
            educations:[],
            faculties:[],
            directions:[],
            groups:[],
            addresses:[],
            subjects:[],
            selectCountry:"",
            selectRegion:"",
            selectDistrict:"",
            isLoading:false,
            addressForEdu:"",
            districtIdForEdu:"",
            eduForUser:"",
            roleNameList:"",
            facultyForEdu:"",
        }
    }

    componentDidMount() {
        axios.get(BASE_URL+"address/getAllDistrict").then((response)=>{
            let district=response.data.object;
            console.log(district)
            this.setState({
                districts:district
            })
        });
        axios.get(BASE_URL+"address/getAllCountry").then((response)=>{
            let district=response.data.object;
            console.log(district)
            this.setState({
                countries:district
            })
        });
        axios.get(BASE_URL+"address/getAllRegion").then((response)=>{
            let district=response.data.object;
            console.log(district)
            this.setState({
                regions:district
            })
        });
        axios.get(BASE_URL+"education/getAllEducation").then((response)=>{
            this.setState({
                educations:response.data.object
            })
        });
    }

    getFacultyByEduId=()=>{


    }

    render() {
        console.log(this.state.roleNameList);
        return (
            <div>
                <div>
                    <div className="modal fade bd-example-modal-lg " tabIndex="-1" role="dialog"
                         aria-labelledby="myLargeModalLabel" aria-hidden="true">
                        <div className="modal-dialog modal-lg">
                            <div className="modal-content">
                                <div className="modal-header">Xodim qo'shish</div>
                                <div className="modal-body">
                                    {this.state.isLoading?<Spinner/>:
                                        <div className="row" id="errorMessageAding">
                                            <div className="form-group col-md-4">
                                                <label htmlFor="countryIdForEdu">Mamlakat</label>
                                                <select className="form-control" onChange={()=>{
                                                    $("#countryIdForEdu").val().length<1?$("#countryIdForEdu").css("border-color",
                                                        "red"):$("#countryIdForEdu").css("border-color","rgb(206, 212, 218)");
                                                    $("#countryIdForEdu").val().length<1?$("#regionIdForEdu").attr("disabled",true):
                                                        $("#regionIdForEdu").attr("disabled",false)
                                                    this.setState({
                                                        selectCountry:$("#countryIdForEdu").val(),
                                                        countryIdForEdu:$("#countryIdForEdu").val()
                                                    })
                                                }} id="countryIdForEdu"
                                                >
                                                    <option></option>
                                                    {this.state.districts.map((item)=>
                                                        <option value={item.region.country.id}>{item.region.country.name}</option>)}
                                                </select>
                                            </div>
                                            <div className="form-group col-md-4">
                                                <label htmlFor="regionIdForEdu">Viloyat</label>
                                                <select className="form-control" onChange={()=>{
                                                    $("#regionIdForEdu").val().length<1?$("#regionIdForEdu").css("border-color",
                                                        "red"):$("#regionIdForEdu").css("border-color","rgb(206, 212, 218)");
                                                    $("#regionIdForEdu").val().length<1?$("#districtIdForEdu").attr("disabled",true):
                                                        $("#districtIdForEdu").attr("disabled",false)
                                                    this.setState({
                                                        selectRegion:$("#regionIdForEdu").val(),
                                                        regionIdForEdu:$("#regionIdForEdu").val()
                                                    })
                                                }} id="regionIdForEdu"
                                                        disabled={true}
                                                >
                                                    <option></option>
                                                    {this.state.districts.map((item)=>
                                                        item.region.country.id===parseInt(this.state.selectCountry)?<option value={item.region.id}>{item.region.name}</option>:""
                                                    )}
                                                </select>
                                            </div>
                                            <div className="form-group col-md-4">
                                                <label htmlFor="districtIdForEdu">Tuman</label>
                                                <select className="form-control" onChange={()=>{
                                                    $("#districtIdForEdu").val().length<1?$("#districtIdForEdu").css("border-color",
                                                        "red"):$("#districtIdForEdu").css("border-color","rgb(206, 212, 218)");
                                                    $("#districtIdForEdu").val().length<1?$("#addressForEdu").attr("disabled",true):
                                                        $("#addressForEdu").attr("disabled",false)
                                                    this.setState({
                                                        selectDistrict:$("#districtIdForEdu").val(),
                                                        districtIdForEdu:$("#districtIdForEdu").val()
                                                    })
                                                }} id="districtIdForEdu"
                                                        disabled={true}>
                                                    <option></option>
                                                    {this.state.districts.map((item)=>
                                                        item.region.id===parseInt(this.state.selectRegion)?<option value={item.id}>{item.name}</option>:"")}
                                                </select>
                                            </div>
                                            <div className="form-group col-md-12">
                                                <label htmlFor="addressForEdu">Manzili</label>
                                                <input type="text" className="form-control" id="addressForEdu"
                                                       disabled={true} onChange={()=>{
                                                    $("#addressForEdu").val().length<8?$("#addressForEdu").css("border-color",
                                                        "red"):$("#addressForEdu").css("border-color","rgb(206, 212, 218)");
                                                    $("#addressForEdu").val().length<8?$("#userFirstName").attr("disabled",true):
                                                        $("#userFirstName").attr("disabled",false)
                                                    this.setState({
                                                        addressForEdu:$("#addressForEdu").val()
                                                    })
                                                }}/>
                                            </div>
                                            <h5 className="col-md-12">Xodim ma'lumotllari</h5>
                                            <div className="form-group col-md-4">
                                                <label htmlFor="userFirstName">Ismi</label>
                                                <input type="text" className="form-control" id="userFirstName"
                                                       disabled={true}   placeholder="Nomi" onChange={()=>{
                                                    $("#userFirstName").val().length<8?$("#userFirstName").css("border-color",
                                                        "red"):$("#userFirstName").css("border-color","rgb(206, 212, 218)");
                                                    $("#userFirstName").val().length<8?$("#userLastName").attr("disabled",true):
                                                        $("#userLastName").attr("disabled",false)
                                                    this.setState({
                                                        inputEducationName:$("#inputEducationName").val()
                                                    })
                                                }}/>
                                            </div>
                                            <div className="form-group col-md-4">
                                                <label htmlFor="userLastName">Familyasi</label>
                                                <input type="text" className="form-control" id="userLastName"
                                                       disabled={true}   placeholder="Nomi" onChange={()=>{
                                                    $("#userLastName").val().length<8?$("#userLastName").css("border-color",
                                                        "red"):$("#userLastName").css("border-color","rgb(206, 212, 218)");
                                                    $("#userLastName").val().length<8?$("#userFathersName").attr("disabled",true):
                                                        $("#userFathersName").attr("disabled",false)
                                                    this.setState({
                                                        inputEducationName:$("#inputEducationName").val()
                                                    })
                                                }}/>
                                            </div>
                                            <div className="form-group col-md-4">
                                                <label htmlFor="userFathersName">Sharifi</label>
                                                <input type="text" className="form-control" id="userFathersName"
                                                       disabled={true}   placeholder="Nomi" onChange={()=>{
                                                    $("#userFathersName").val().length<8?$("#inputEducationName").css("border-color",
                                                        "red"):$("#userFathersName").css("border-color","rgb(206, 212, 218)");
                                                    $("#userFathersName").val().length<8?$("#userRoleName").attr("disabled",true):
                                                        $("#userRoleName").attr("disabled",false)
                                                    this.setState({
                                                        inputEducationName:$("#inputEducationName").val()
                                                    })
                                                }}/>
                                            </div>
                                            <div className="form-group col-md-2">
                                                <label htmlFor="userRoleName">Lavozimi</label>
                                                <select type="text" disabled={true}
                                                        className="form-control" id="userRoleName" onChange={()=>{
                                                    $("#userRoleName").val().length<2?$("#userRoleName").css("border-color",
                                                        "red"):$("#userRoleName").css("border-color","rgb(206, 212, 218)");
                                                    $("#userRoleName").val().length<2?$("#eduForUser").attr("disabled",true):
                                                        $("#eduForUser").attr("disabled",false)

                                                    this.setState({
                                                        roleNameList:$("#userRoleName").val()
                                                    })
                                                    $("#userRoleName").val()==="ROLE_STUDENT"?$("#forStudent").show():
                                                        $("#forStudent").hide()

                                                }}
                                                >
                                                    <option></option>
                                                    <option value="ROLE_DEAN">Dekan</option>
                                                    <option value="ROLE_DEPUTY_DEAN">Dekan muovini</option>
                                                    <option value="ROLE_TEACHER">O'qituvchi</option>
                                                    <option value="ROLE_STUDENT">Talaba</option>
                                                </select>
                                            </div>
                                            <div className="form-group col-md-4">
                                                <label htmlFor="eduForUser">Muassasa</label>
                                                <select type="text" className="form-control"  id="eduForUser"
                                                       disabled={true} onChange={()=>{
                                                    $("#eduForUser").val().length<1?$("#facultyForEdu").attr("disabled",true):
                                                        $("#facultyForEdu").attr("disabled",false)

                                                    axios.get(BASE_URL+"education/getFacultyByEduId/"+$("#eduForUser").val()).then((res)=>{
                                                        this.setState({
                                                            faculties:res.data.object
                                                        })
                                                    })
                                                }}
                                                >
                                                    <option value=""></option>
                                                    {this.state.educations.map((edu)=>
                                                        <option value={edu.id}>{edu.name}</option>
                                                    )}

                                                </select>
                                            </div>

                                            <div className="form-group col-md-4">
                                                <label htmlFor="facultyForEdu">Fakultet</label>
                                                <select type="text" className="form-control"  id="facultyForEdu"
                                                       disabled={true} onChange={()=>{
                                                    $("#facultyForEdu").val().length<1?$("#facultyForEdu").css("border-color",
                                                        "red"):$("#facultyForEdu").css("border-color","rgb(206, 212, 218)");
                                                    $("#facultyForEdu").val().length<1?$("#directionForUser").attr("disabled",true):
                                                        $("#directionForUser").attr("disabled",false)

                                                    this.setState({
                                                        facultyForEdu:$("#facultyForEdu").val()
                                                    })
                                                    axios.get(BASE_URL+"education/getDirectionByFacultyId/"+$("#facultyForEdu").val()).then((res)=>{
                                                        this.setState({
                                                            directions:res.data.object
                                                        })
                                                    })
                                                }}
                                                >
                                                    <option value=""></option>
                                                    {this.state.faculties.map((faculty)=>
                                                            <option value={faculty.id}>{faculty.name}</option>
                                                        )

                                                    }
                                                </select>
                                            </div>

                                            <div id="forStudent" className="col-md-12 row" style={{display:"none"}}>
                                                <div className="form-group col-md-6">
                                                    <label htmlFor="directionForUser">Yo'nalish</label>
                                                    <select type="text" className="form-control"  id="directionForUser"
                                                            disabled={true} onChange={()=>{
                                                        $("#directionForUser").val().length<1?$("#directionForUser").css("border-color",
                                                            "red"):$("#directionForUser").css("border-color","rgb(206, 212, 218)");
                                                        $("#directionForUser").val().length<1?$("#groupForUser").attr("disabled",true):
                                                            $("#groupForUser").attr("disabled",false)
                                                        this.setState({
                                                            directionForUser:$("#directionForUser").val()
                                                        })

                                                        axios.get(BASE_URL+"education/getGroupByDirectionId/"+$("#directionForUser").val()).then((res)=>{
                                                            this.setState({
                                                                groups:res.data.object
                                                            })
                                                        })
                                                    }}
                                                    >
                                                        <option value=""></option>
                                                        {this.state.directions.map((dir)=>
                                                            <option value={dir.id}>{dir.name}</option>
                                                        )

                                                        }
                                                    </select>
                                                </div>
                                                <div className="form-group col-md-4">
                                                    <label htmlFor="groupForUser">Guruh</label>
                                                    <select type="text" className="form-control"  id="groupForUser"
                                                            disabled={true} onChange={()=>{
                                                        $("#groupForUser").val().length<1?$("#groupForUser").css("border-color",
                                                            "red"):$("#groupForUser").css("border-color","rgb(206, 212, 218)");

                                                        this.setState({
                                                            groupForUser:$("#groupForUser").val()
                                                        })
                                                    }}
                                                    >
                                                        <option value=""></option>
                                                        {this.state.groups.map((gr)=>
                                                            <option value={gr.id}>{gr.number}</option>
                                                        )

                                                        }
                                                    </select>
                                                </div>
                                            </div>
                                            <div className="form-group col-md-2">
                                                <button className="btn btn-success" id="addEducationBtn"
                                                    // disabled={true}
                                                        style={{marginTop:"30px"}}
                                                        onClick={this.createElement}>
                                                    Saqlash
                                                </button>
                                            </div>
                                        </div>
                                    }
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default EmployeeModal;