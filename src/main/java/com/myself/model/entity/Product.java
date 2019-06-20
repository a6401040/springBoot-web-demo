package com.myself.model.entity;

import java.io.Serializable;

/**
 * Product bean
 * @author zhangqiling
 * @date 2019/6/20
 * @version V1.0
 */

public class Product implements Serializable {
    private static final long serialVersionUID = 1435515995276255188L;


    private long id;
    private String name;
    private long price;

    /**
     * Gets the value of serialVersionUID.
     *
     * @return the value of serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * Gets the value of id.
     *
     * @return the value of id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * <p>You can use getId() to get the value of id</p>
     *
     * @param id id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets the value of name.
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * <p>You can use getName() to get the value of name</p>
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the value of price.
     *
     * @return the value of price
     */
    public long getPrice() {
        return price;
    }

    /**
     * Sets the price.
     *
     * <p>You can use getPrice() to get the value of price</p>
     *
     * @param price price
     */
    public void setPrice(long price) {
        this.price = price;
    }
}
