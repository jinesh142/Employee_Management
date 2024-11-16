import axios from "axios";

const REST_BASE_URL = "http://localhost:8081/api/employees";

export const listEmployess = () => {
  return axios.get(REST_BASE_URL);
};

export const saveEmployee = (employee) => axios.post(REST_BASE_URL, employee);

export const getEmployee = (id) => axios.get(REST_BASE_URL + '/' +id.id);

export const updateEmployee = (id , employee) => axios.put(REST_BASE_URL + '/' +id.id,employee );

export const deleteEmployeeById = (id) => axios.delete(REST_BASE_URL + '?id=' + id);