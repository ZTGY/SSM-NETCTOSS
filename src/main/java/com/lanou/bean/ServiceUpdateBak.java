package com.lanou.bean;

/**
 * @author dllo
 */
public class ServiceUpdateBak {
    private Integer id;

    private Integer serviceId;

    private Integer costId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getCostId() {
        return costId;
    }

    public void setCostId(Integer costId) {
        this.costId = costId;
    }

    @Override
    public String toString() {
        return "ServiceUpdateBak{" +
                "id=" + id +
                ", serviceId=" + serviceId +
                ", costId=" + costId +
                '}';
    }
}