import React, {useEffect, useState} from "react";
import Table from 'react-bootstrap/Table';
import {API} from "../../../utils/api";
import Dropdown from 'react-bootstrap/Dropdown';
import {DropdownButton} from "react-bootstrap";
import {useNavigate} from 'react-router-dom';
import Button from "react-bootstrap/Button";
import {IClient} from "../interfaces/interfaces";


export function List() {

    const [clients, setClients] = useState<IClient[]>([]);
    const navigate = useNavigate();

    useEffect(() => {
        API.get<IClient[]>("/client").then(({data}) =>
            setClients(data))
    }, [])

    function deleteClient(client: IClient) {
        API.delete(`/client/${client.id}`).then(data => {
            alert("Client has been deleted");
            setClients(clients.filter(obj => obj.id !== client.id));
        }).catch(err => {
            alert(err.response.data)
        })
    }


    return (
        <Table striped size="sm">
            <thead>
            <tr>
                <th>Name</th>
                <th>CPF</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Buy</th>
                <th>Settings</th>

            </tr>
            </thead>
            <tbody>
            {clients.map(client => {
                return (
                    <tr>
                        <td>{client.name}</td>
                        <td>{client.cpf}</td>
                        <td>{client.email}</td>
                        <td>{client.phone}</td>
                        <td><Button onClick={e => navigate(`/product/${client.id}`)}>Buy</Button></td>
                        <td><DropdownButton title="More" id="bg-nested-dropdown">
                            <Dropdown.Item eventKey="1" onClick={e => navigate(`/client/update/${client.id}`)}>Update</Dropdown.Item>
                            <Dropdown.Item eventKey="2" onClick={e => deleteClient(client)}>Delete</Dropdown.Item>
                        </DropdownButton></td>

                    </tr>
                );
            })}
            </tbody>

        </Table>
    );
}
