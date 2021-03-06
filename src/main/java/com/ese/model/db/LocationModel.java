package com.ese.model.db;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "location")
@Proxy(lazy=false)
public class LocationModel {
    @Id
    private int id;

    @Column(name = "location_barcode")
    private String locationBarcode;

    @Column(name = "location_name")
    private String locationName;

    @OneToOne
    @JoinColumn(name = "warehouse_id")
    private WarehouseModel warehouseId;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "remark")
    private String remark;

    @Column(name = "qty")
    private int qty;

    @Column(name = "status")
    private String status;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "isvalid", nullable=false, columnDefinition="int default 0")
    private int isvalid;

    @Column(name = "version", nullable=false, columnDefinition="int default 0")
    private Integer version;

    @Column(name = "reserved_qty")
    private int reservedQty;

    @Column(name = "ismix", nullable=false, columnDefinition="int default 0")
    private int ismix;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("locationBarcode", locationBarcode)
                .append("locationName", locationName)
                .append("warehouseId", warehouseId)
                .append("capacity", capacity)
                .append("remark", remark)
                .append("qty", qty)
                .append("status", status)
                .append("createBy", createBy)
                .append("createDate", createDate)
                .append("updateBy", updateBy)
                .append("updateDate", updateDate)
                .append("isvalid", isvalid)
                .append("version", version)
                .append("reservedQty", reservedQty)
                .append("ismix", ismix)
                .toString();
    }
}
