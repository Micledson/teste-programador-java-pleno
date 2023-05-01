import React from "react";
import {List} from "../../components/product/List";
import Button from "react-bootstrap/Button";
import {Link} from 'react-router-dom';
import {BackButton} from "../../components/BackButton";
export function Product() {
    return <>
        <List/>
        <Link to="/product/create"><Button>Create product</Button></Link>
        <BackButton/>
    </>
}
