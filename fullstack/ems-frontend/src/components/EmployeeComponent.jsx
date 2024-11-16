import React, { useEffect } from "react";
import { useState } from "react";
import { saveEmployee, getEmployee } from "../services/EmployeService";
import { useNavigate, useParams } from "react-router-dom";

const EmployeeComponent = () => {
  const [formData, setFormData] = useState({
    firstName: "",
    lastName: "",
    email: ""
  });

  const [errros, setErrors] = useState({
    firstName: "",
    lastName: "",
    email: "",
  });
  const navigater = useNavigate();

  const id = useParams();

  useEffect(() => {
    if (id) {
      getEmployee(id)
        .then((response) => {
          setFormData(response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    }
  }, [id]);
  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(formData);
    if (validateForm()) {
      saveEmployee(formData).then((response) => {
        console.log(response.data);
        navigater("/employees");
      });
    }
  };

  function validateForm() {
    let valid = true;
    const errorCopy = { ...errros };
    if (formData.firstName.trim()) {
      errorCopy.firstName = "";
    } else {
      errorCopy.firstName = "firstName required";
      valid = false;
    }

    if (formData.lastName.trim()) {
      errorCopy.lastName = "";
    } else {
      errorCopy.lastName = "lastName required";
      valid = false;
    }

    if (formData.email.trim()) {
      errorCopy.email = "";
    } else {
      errorCopy.email = "email required";
      valid = false;
    }
    setErrors(errorCopy);

    return valid;
  }

  function pageTitle() {
    if (id) {
      return <h2 className="text-center">Update Employee </h2>;
    } else {
      return <h2 className="text-center">Add Employee </h2>;
    }
  }
  return (
    <div className="container">
      <div className="row">
        <div className="card">
          {pageTitle()}

          <form onSubmit={handleSubmit}>
            <div className="form-group row">
              <label className="col-sm-2 col-form-label">First Name:</label>
              <div className="col-sm-10">
                <input
                  type="text"
                  name="firstName"
                  value={formData.firstName}
                  onChange={handleChange}
                  className={`form-control ${
                    errros.firstName ? `is-invalid` : ``
                  }`}
                />
                {errros.firstName && (
                  <div className="invalid-feedback">{errros.firstName} </div>
                )}
              </div>
            </div>
            <div className="form-group row">
              <label className="col-sm-2 col-form-label">Last Name:</label>
              <div className="col-sm-10">
                <input
                  type="text"
                  name="lastName"
                  value={formData.lastName}
                  onChange={handleChange}
                  className={`form-control ${
                    errros.lastName ? `is-invalid` : ``
                  }`}
                />
                {errros.lastName && (
                  <div className="invalid-feedback">{errros.lastName} </div>
                )}
              </div>
            </div>
            <div className="form-group row">
              <label className="col-sm-2 col-form-label">Email: </label>
              <div className="col-sm-10">
                <input
                  type="email"
                  name="email"
                  value={formData.email}
                  onChange={handleChange}
                  className={`form-control ${errros.email ? `is-invalid` : ``}`}
                />
                {errros.email && (
                  <div className="invalid-feedback">{errros.email} </div>
                )}
              </div>
            </div>
            <button className="btn btn-success" type="submit">
              Submit
            </button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default EmployeeComponent;
