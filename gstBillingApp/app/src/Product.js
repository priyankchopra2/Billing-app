import React, { Component } from "react";

class Product extends Component {
  state = {
    isLoading: true,
    Products: [],
  };

  async componentDidMount() {
    const response = await fetch("/prods");
    const body = await response.json();
    this.setState({ Products: body, isLoading: false });
  }

  render() {
    const { Products, isLoading } = this.state;

    if (isLoading) {
      return <div>Loading.....</div>;
    } else {
      return (
        <div>
          <h2>Product</h2>
          {Products.map((prod) => (
            <div id={prod.id}>{prod.name}</div>
          ))}
        </div>
      );
    }
  }
}

export default Product;
