package com.rapidtech.restapi.entity;

import com.rapidtech.restapi.model.PurchaseOrderModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "po_tab")
public class PurchaseOrderEntity {
    @Id
    @TableGenerator(name = "po_id_generator", table = "sequence_tab",
            pkColumnName = "gen_name", valueColumnName = "gen_value",
            pkColumnValue="po_id", initialValue=0, allocationSize=0)

    @GeneratedValue(strategy = GenerationType.TABLE, generator = "po_id_generator")
    private Integer id;

    @Column(name = "po_code", length = 20, nullable = false)
    private String poCode;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private CustomerEntity customer;

    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id", insertable = false, updatable = false)
    private EmployeeEntity employee;

    @Column(name = "shipper_id", nullable = false)
    private Long shipperId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipper_id", insertable = false, updatable = false)
    private ShipperEntity shipper;

    @Temporal(TemporalType.DATE)
    @Column(name = "po_date", nullable = false)
    private Date poDate;

    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;

    public PurchaseOrderEntity(PurchaseOrderModel model) {
        BeanUtils.copyProperties(model, this);
    }
}