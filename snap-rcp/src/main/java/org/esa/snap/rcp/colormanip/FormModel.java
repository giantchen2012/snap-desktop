package org.esa.snap.rcp.colormanip;

import org.esa.snap.framework.datamodel.Band;
import org.esa.snap.framework.datamodel.ImageInfo;
import org.esa.snap.framework.datamodel.Product;
import org.esa.snap.framework.datamodel.RasterDataNode;
import org.esa.snap.framework.ui.product.ProductSceneView;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Component;

/**
 * @author Norman Fomferra
 */
class FormModel {
    private ProductSceneView productSceneView;
    private ImageInfo modifiedImageInfo;

    public String getTitlePrefix() {
        return Bundle.CTL_ColorManipulationTopComponent_Name();
    }

    public ProductSceneView getProductSceneView() {
        return productSceneView;
    }

    public void setProductSceneView(ProductSceneView productSceneView) {
        this.productSceneView = productSceneView;
    }

    public RasterDataNode getRaster() {
        return getProductSceneView().getRaster();
    }

    public RasterDataNode[] getRasters() {
            return getProductSceneView().getRasters();
        }


    public void setRasters(RasterDataNode[] rasters) {
        getProductSceneView().setRasters(rasters);
    }

    public ImageInfo getOriginalImageInfo() {
        return getProductSceneView().getImageInfo();
    }

    /**
     * @return The image info being edited.
     */
    public ImageInfo getModifiedImageInfo() {
        return modifiedImageInfo;
    }

    /**
     * Sets modifiedImageInfo to a copy of the given imageInfo.
     * @param imageInfo The image info from which a copy is made which will then be edited.
     */
    public void setModifiedImageInfo(ImageInfo imageInfo) {
        this.modifiedImageInfo = imageInfo.createDeepCopy();
    }

    public void applyModifiedImageInfo() {
        getProductSceneView().setImageInfo(getModifiedImageInfo());
    }

    public String getModelName() {
        return getProductSceneView().getSceneName();
    }

    public Product getProduct() {
        return getProductSceneView().getProduct();
    }

    public boolean isValid() {
        return getProductSceneView() != null;
    }

    public boolean isContinuous3BandImage() {
        return isValid() && getProductSceneView().isRGB();
    }

    public boolean isContinuous1BandImage() {
        return isValid() && getRaster() instanceof Band && ((Band) getRaster()).getIndexCoding() == null;
    }

    public boolean isDiscrete1BandImage() {
        return isValid() && getRaster() instanceof Band && ((Band) getRaster()).getIndexCoding() != null;
    }

    public boolean canUseHistogramMatching() {
        return true;
    }

    public boolean isMoreOptionsFormCollapsedOnInit() {
        return true;
    }

    public void modifyMoreOptionsForm(MoreOptionsForm moreOptionsForm) {
    }

    public void updateMoreOptionsFromImageInfo(MoreOptionsForm moreOptionsForm) {
    }

    public void updateImageInfoFromMoreOptions(MoreOptionsForm moreOptionsForm) {
    }

    public Component createEmptyContentPanel() {
        return new JLabel("<html>This tool window is used to manipulate the<br>" +
                          "<b>colouring of images</b> shown in an image view.<br>" +
                          " Right now, there is no selected image view.", SwingConstants.CENTER);
    }

}
