package com.ese.beans;

import com.ese.model.ConveyorLineModel;
import com.ese.model.WarehouseModel;
import com.ese.model.view.PalletMeanegementView;
import com.ese.utils.Utils;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "palletManegement")
@ViewScoped
public class PalletManegementBean extends Bean implements Serializable {

    @Getter @Setter
    List<WarehouseModel> warehouseModelList;
    @Getter
    WarehouseModel warehouseMode;
    @Getter @Setter
    List<ConveyorLineModel> conveyorLineModelList;
    @Getter
    ConveyorLineModel conveyorLineModel;
    @Getter
    List<PalletMeanegementView> palletMeanegementViewList;
    @Getter
    PalletMeanegementView palletMeanegementView;

    @Getter @Setter
    int statusOnShow;

    @PostConstruct
    public void onCreattion(){
        log.debug("onCreattion().");
        palletMeanegementView = new PalletMeanegementView();
        warehouseMode = new WarehouseModel();
        conveyorLineModel = new ConveyorLineModel();
        init();
    }

    private void init(){
        log.debug("init().");
        warehouseModelList = warehouseService.getWarehouseList();
        conveyorLineModelList = conveyorLineService.getConveyorLineList();
        statusOnShow = 0;
        onloadPallet();
    }

    private void onloadPallet(){
        log.debug("onloadPallet(). ");
        palletMeanegementViewList = palletService.findPalletJoinLocation();
    }

    public void onfind(){
        log.debug("changeOn : {}", statusOnShow);
        palletMeanegementViewList = palletService.findByChang(statusOnShow, warehouseMode.getId(), conveyorLineModel.getId());
    }
}