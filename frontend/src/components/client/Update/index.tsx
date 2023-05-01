import React from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import InputMask from "react-input-mask";
import { useForm } from "react-hook-form";
import {API} from "../../../utils/api";
import {useParams, useNavigate} from 'react-router-dom';
import {BackButton} from "../../BackButton";
import {IClient} from "../interfaces/interfaces";
import {isValidPhone} from "../../../utils/clientValidate";
export function Update() {
    const { register, handleSubmit} = useForm();

    const navigate = useNavigate();

    const {id} = useParams();

    function submit(client: any) {
        const phone = client.phone?.replace(/[^0-9]/gi, '')

        if(phone.length > 0 && !isValidPhone(client.phone)) {
            alert("Invalid phone")
            return;
        } else client.phone = null

        if(client.email === "") client.email = null
        if(client.name === "") client.name = null

        API.put<IClient>(`client/${id}`, client).then(data => {
            alert("Client successfully updated")
            navigate("/")
        }).catch(err => {
            alert(`Error: ${err.response.data}`)
        });
    }

    return (
        <Form onSubmit={handleSubmit(submit)}>
            <Form.Group className="mb-3" controlId="formName">
                <Form.Label>Name</Form.Label>
                <Form.Control type="name" placeholder="Enter name" required={false}
                              {...register("name")}  />
            </Form.Group>
            <Form.Group className="mb-3" controlId="formEmail">
                <Form.Label>Email</Form.Label>
                <Form.Control type="Email" placeholder="Enter Email" required={false}
                              {...register("email")}/>
            </Form.Group>

            <Form.Group className="mb-3" controlId="formPhone">
                <Form.Label>Phone</Form.Label>
                <Form.Control type="name" placeholder="Enter Phone" required={false}
                              {...register("phone")} as={InputMask} mask="(99) 99999-9999" />
            </Form.Group>
            <Button variant="primary" type="submit">
                Submit
            </Button>
            <BackButton/>
        </Form>
    );
}