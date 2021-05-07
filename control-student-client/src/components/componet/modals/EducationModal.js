import React, {Component} from 'react';
import $ from 'jquery'
import axios from "axios";
import {BASE_URL} from "../../const/constants";
import {Spinner} from "../Spinner";

let showMessage1=false;
let showMessage2=false;
class EducationModal extends Component {
    constructor(props) {
        super(props);
        this.state={
            countries:[],
            regions:[],
            districts:[],
            educations:[],
            addresses:[],
            selectCountry:"",
            selectRegion:"",
            selectDistrict:"",
            isLoading:false,
            addressForEdu:"",
            districtIdForEdu:"",
            inputEducationName:"",
            educationType:"",
            facultyForEdu:"",
            directionForEducation:"",
            groupForEdu:"",
            courseFor:""
        };
    }

    componentDidMount() {
        axios.get(BASE_URL+"address/getAllDistrict").then((response)=>{
            this.setState({
                districts:response.data.object
            })
        })

        axios.get(BASE_URL+"education/getAllEducation").then((response)=>{
            console.log(response.data);
            this.setState({
                educations:response.data.object,
                isLoading:false
            })
        })
    }

    createElement=()=> {
        let addressForEdu= this.state.addressForEdu;
        let districtIdForEdu= this.state.districtIdForEdu;
        let inputEducationName=this.state.inputEducationName;
        let educationType=this.state.educationType;
        let facultyForEdu=this.state.facultyForEdu;
        let directionForEducation=this.state.directionForEducation;
        let groupForEdu=this.state.groupForEdu;
        let courseFor=this.state.courseFor;
        console.log("Ishladi");

        let address={
            id:null,
            address:addressForEdu,
            districtId:districtIdForEdu,
            addrType:"HOME_PLACE",
        };
        if (
            addressForEdu||
            districtIdForEdu||
            inputEducationName||
            educationType||
            facultyForEdu||
            directionForEducation||
            groupForEdu||
            courseFor
        ){
            this.setState({
                isLoading:true
            });
            axios.post(BASE_URL+"address/saveOrEditAddress",address).then((re)=>{
                console.log(re.data);
                let education={
                    id:null,
                    name:inputEducationName,
                    eduType:educationType,
                    startDate:null,
                    endDate:null,
                    addresId:re.data.object.id,
                    complete:false
                };
                axios.post(BASE_URL+"education/saveOrEditEducation",education).then((res)=>{
                    console.log(res.data);
                    let faculty={
                        id:null,
                        name:facultyForEdu,
                        educationId:res.data.object.id
                    };
                    axios.post(BASE_URL+"education/saveOrEditFaculty",faculty).then((resp)=>{
                        console.log(resp.data);
                        let direction={
                            id:null,
                            name:directionForEducation,
                            faculty:resp.data.object.id
                        };
                        axios.post(BASE_URL+"education/saveOrEditDirection",direction).then((respo)=>{
                            console.log(respo.data);
                            let group={
                                id:null,
                                number:groupForEdu,
                                course_number:courseFor,
                                directionId: respo.data.object.id
                            };
                            axios.post(BASE_URL+"education/saveOrEditGroup",group).then((response)=>{
                                console.log(response.data);
                                this.setState({
                                    isLoading:false
                                });
                            })
                        })
                    })
                });
            });
        }
        else{
            $("#errorMessageAding").prepend("<p class=\"text-danger col-sm-12\">Iltimos barcha maydonlarni to'ldirib qayta urunib ko'ring!!!</p>");
        }
    };



    render() {
        return (
            <div>
                <div>
                    <div className="modal fade bd-example-modal-lg " tabIndex="-1" role="dialog"
                         aria-labelledby="myLargeModalLabel" aria-hidden="true">
                        <div className="modal-dialog modal-lg">
                            <div className="modal-content">
                                <div className="modal-header">Ta'lim muassasasi qo'shish</div>
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
                                                    $("#addressForEdu").val().length<8?$("#inputEducationName").attr("disabled",true):
                                                        $("#inputEducationName").attr("disabled",false)
                                                    this.setState({
                                                        addressForEdu:$("#addressForEdu").val()
                                                    })
                                                }}/>
                                            </div>
                                            <div className="form-group col-md-9">
                                                <label htmlFor="inputEducationName">Ta'lim markazi nomi</label>
                                                <input type="text" className="form-control" id="inputEducationName"
                                                       disabled={true}   placeholder="Nomi" onChange={()=>{
                                                    $("#inputEducationName").val().length<8?$("#inputEducationName").css("border-color",
                                                        "red"):$("#inputEducationName").css("border-color","rgb(206, 212, 218)");
                                                    $("#inputEducationName").val().length<8?$("#educationType").attr("disabled",true):
                                                        $("#educationType").attr("disabled",false)
                                                    this.setState({
                                                        inputEducationName:$("#inputEducationName").val()
                                                    })
                                                }} list="inputEducationName"/>
                                                    <datalist id="inputEducationName">
                                                        {
                                                            this.state.educations.map((item)=>
                                                            <option value={item.id}>{item.name}</option>
                                                            )
                                                        }
                                                    </datalist>
                                            </div>
                                            <div className="form-group col-md-3">
                                                <label htmlFor="educationType">Ta'lim turi</label>
                                                <select type="text" disabled={true}
                                                        className="form-control" id="educationType" onChange={()=>{
                                                    $("#educationType").val().length<2?$("#educationType").css("border-color",
                                                        "red"):$("#educationType").css("border-color","rgb(206, 212, 218)");
                                                    $("#educationType").val().length<2?$("#facultyForEdu").attr("disabled",true):
                                                        $("#facultyForEdu").attr("disabled",false)
                                                    this.setState({
                                                        educationType:$("#educationType").val()
                                                    })
                                                }}
                                                >
                                                    <option></option>
                                                    <option value="HIGHER_EDUCATION">Oliy</option>
                                                    <option value="SECONDARY_EDUCATION">O'rta</option>
                                                    <option value="SECONDARY_SPECIAL_EDUCATION">O'rta maxsus</option>
                                                </select>
                                            </div>
                                            <div className="form-group col-md-12">
                                                <label htmlFor="facultyForEdu">Fakultet</label>
                                                <input type="text" className="form-control"  id="facultyForEdu"
                                                       disabled={true} onChange={()=>{
                                                    $("#facultyForEdu").val().length<8?$("#facultyForEdu").css("border-color",
                                                        "red"):$("#facultyForEdu").css("border-color","rgb(206, 212, 218)");
                                                    $("#facultyForEdu").val().length<8?$("#directionForEducation").attr("disabled",true):
                                                        $("#directionForEducation").attr("disabled",false)
                                                    this.setState({
                                                        facultyForEdu:$("#facultyForEdu").val()
                                                    })
                                                }}
                                                />
                                            </div>
                                            <div className="form-group col-md-12">
                                                <label htmlFor="directionForEducation">Yo'nalish</label>
                                                <input type="text" className="form-control" id="directionForEducation"
                                                       disabled={true} onChange={()=>{
                                                    $("#directionForEducation").val().length<8?$("#directionForEducation").css("border-color",
                                                        "red"):$("#directionForEducation").css("border-color","rgb(206, 212, 218)");
                                                    $("#directionForEducation").val().length<8?$("#groupForEdu").attr("disabled",true):
                                                        $("#groupForEdu").attr("disabled",false)
                                                    this.setState({
                                                        directionForEducation:$("#directionForEducation").val()
                                                    })
                                                }}/>
                                            </div>
                                            <div className="form-group col-md-8" >
                                                <label htmlFor="groupForEdu">Guruh</label>
                                                <input type="text" className="form-control" id="groupForEdu"
                                                       disabled={true} onChange={()=>{
                                                    $("#groupForEdu").val().length<8?$("#groupForEdu").css("border-color",
                                                        "red"):$("#groupForEdu").css("border-color","rgb(206, 212, 218)");
                                                    $("#groupForEdu").val().length<8?$("#courseFor").attr("disabled",true):
                                                        $("#courseFor").attr("disabled",false)
                                                    this.setState({
                                                        groupForEdu:$("#groupForEdu").val()
                                                    })
                                                }}/>
                                            </div>
                                            <div className="form-group col-md-2">
                                                <label htmlFor="courseFor">Kurs</label>
                                                <input type="text" maxLength={1} className="form-control" id="courseFor"
                                                       disabled={true} onChange={()=>{
                                                    $("#courseFor").val().length<1?$("#courseFor").css("border-color",
                                                        "red"):$("#groupForEdu").css("border-color","rgb(206, 212, 218)");
                                                    $("#courseFor").val().length<1?$("#addEducationBtn").attr("disabled",true):
                                                        $("#addEducationBtn").attr("disabled",false)
                                                    this.setState({
                                                        courseFor:$("#courseFor").val()
                                                    })
                                                }}/>
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

export default EducationModal;