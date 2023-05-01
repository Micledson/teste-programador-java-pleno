import React from "react";
import {List} from "../../components/client/List";
import Button from "react-bootstrap/Button";

import {Link} from 'react-router-dom';
export function Client() {
    return (
        <>
            <List/>
            <Link to="/client/create"><Button>Create client</Button></Link>
            <Link to="/product"><Button>Products</Button></Link>
            <Link to="/order"><Button>Order</Button></Link>
        </>
    )
}
