import React, {useEffect, useState} from "react";
import Table from 'react-bootstrap/Table';
import {API} from "../../../utils/api";
import Dropdown from 'react-bootstrap/Dropdown';
import {DropdownButton} from "react-bootstrap";
import {useNavigate} from 'react-router-dom';
import Button from "react-bootstrap/Button";
import {BackButton} from "../../BackButton";
import {IOrder} from "../interfaces/interfaces";


export function List() {

    const [orders, setOrders] = useState<IOrder[]>([]);
    const navigate = useNavigate();

    useEffect(() => {
        API.get<IOrder[]>("/order").then(({data}) => {
            setOrders(data)
            }
        )}, [])

    return (
        <>

            <Table striped size="sm">
                <thead>
                <tr>
                    <th>Description</th>
                    <th>Date</th>
                    <th>Total Value(R$)</th>
                </tr>
                </thead>
                <tbody>
                {orders.map(order => {
                    return (
                        <tr>
                            <td>{order.description}</td>
                            <td>{order.date}</td>
                            <td>{order.totalValue}</td>
                        </tr>
                    );
                })}
                </tbody>


            </Table>
            <BackButton/>
        </>
    );
}
