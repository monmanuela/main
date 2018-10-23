package seedu.address.storage;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.OrderBook;
import seedu.address.model.ReadOnlyOrderBook;
import seedu.address.model.deliveryman.DeliverymenList;
import seedu.address.storage.deliveryman.XmlSerializableDeliverymenList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "foodzoom")
public class XmlFoodZoom {
    @XmlElement(required = true)
    private XmlSerializableOrderBook orderBook;

    @XmlElement(required = true)
    private XmlSerializableDeliverymenList deliverymenList;

    private DeliverymenList modelDeliverymenList;
    private OrderBook modelOrderBook;

    public XmlFoodZoom() {
        orderBook = new XmlSerializableOrderBook();
        deliverymenList = new XmlSerializableDeliverymenList();
    }

    public XmlFoodZoom(ReadOnlyOrderBook ordersSrc, DeliverymenList deliverymenSrc) {
        orderBook = new XmlSerializableOrderBook(ordersSrc);
        deliverymenList = new XmlSerializableDeliverymenList(deliverymenSrc);
    }

    public void getModelTypes() throws IllegalValueException {
        modelOrderBook = orderBook.toModelType();
        modelDeliverymenList = deliverymenList.toModelTypeWithOrders(modelOrderBook);
    }

    public OrderBook getModelOrderBook() {
        return modelOrderBook;
    }

    public DeliverymenList getModelDeliverymenList() {
        return modelDeliverymenList;
    }
}
