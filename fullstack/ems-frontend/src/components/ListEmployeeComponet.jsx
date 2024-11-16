import React, { useEffect, useState } from "react";
import { listEmployess ,deleteEmployeeById } from "../services/EmployeService";
import { useNavigate } from "react-router-dom";

const ListEmployeeComponent = () => {
  const [employees, setEmployee] = useState([]);

  const navigator = useNavigate();

  useEffect(() => {
    getAllEmployee();
  }, []);

  function getAllEmployee(){
    listEmployess()
    .then((response) => {
      setEmployee(response.data);
    })
    .catch((error) => {
      console.log(error);
    });
  }
  function addNewEmployee() {
    navigator("/add-employee");
  }

  function updateEmployee(id) {
    navigator(`/edit-employee/${id}`);
  }

  function deleteEmployee(id) {
    deleteEmployeeById(id)
      .then((response) => {
        getAllEmployee();
      })
      .catch((error) => {
        console.log(error);
      });
  }
  return (
    <div className="container">
      <h2>Employee List</h2>
      <button className="btn btn-primary mb-2" onClick={addNewEmployee}>
        Add Employee
      </button>
      <table className="table table-striped table-bordered">
        <thead>
          <tr>
            <th>Id</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {employees.map((employee) => (
            <tr key={employee.id}>
              <td>{employee.id}</td>
              <td>{employee.firstName}</td>
              <td>{employee.lastName}</td>
              <td>{employee.email}</td>
              <td>
                <button
                  className="btn btn-info"
                  onClick={() => updateEmployee(employee.id)}
                >
                  Update
                </button>{" "}
                <button
                  className="btn btn-danger"
                  onClick={() => deleteEmployee(employee.id)}
                >
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};
export default ListEmployeeComponent;
