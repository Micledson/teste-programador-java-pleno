import React from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import {useForm} from "react-hook-form";
import {API} from "../../../utils/api";
import {useNavigate } from 'react-router-dom';
import {BackButton} from "../../BackButton";
import {IProduct} from "../interfaces/interfaces";

export function Create() {

    const {register, handleSubmit} = useForm();
    const navigate = useNavigate();


    function submit(product: any) {
        API.post<IProduct>("/product", product).then(data => {
            alert("Product successfully created")
            navigate(-1);
        }).catch(err => {
            alert(`Error: ${err.response.data}`)
        });
    }

    return (
        <Form onSubmit={handleSubmit(submit)}>
            <Form.Group className="mb-3" controlId="formDescription">
                <Form.Label>Description</Form.Label>
                <Form.Control type="text" placeholder="Enter description" required={true}
                              {...register("description")}  />
            </Form.Group>
            <Form.Group className="mb-3" controlId="formUnity">
                <Form.Label>Unities</Form.Label>
                <Form.Control type="number" placeholder="Enter unity" required={true} min={1}
                              {...register("unity")}/>
            </Form.Group>

            <Form.Group className="mb-3" controlId="formPrice">
                <Form.Label>Price</Form.Label>
                <Form.Control type="number" placeholder="Enter price" required={true} min="0.00" max="10000.00" step="0.01"
                              {...register("price")}/>
            </Form.Group>

            <Button variant="primary" type="submit">
                Submit
            </Button>

            <BackButton/>
        </Form>
    );
}