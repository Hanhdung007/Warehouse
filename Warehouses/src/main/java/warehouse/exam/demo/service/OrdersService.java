package warehouse.exam.demo.service;

import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import warehouse.exam.demo.model.Orders;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javassist.NotFoundException;
import warehouse.exam.demo.model.TbSYSSEQUENCE;
import warehouse.exam.demo.reponsitory.OrdersRepository;

@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final SysSequenceService sysSequenceService;
    private final OrdercodeEAN13Service ordercodeEAN13Service;

    @Autowired
    public OrdersService(OrdersRepository ordersRepository, SysSequenceService sysSequenceService, OrdercodeEAN13Service ordercodeEAN13Service) {
        this.ordersRepository = ordersRepository;
        this.sysSequenceService = sysSequenceService;
        this.ordercodeEAN13Service = ordercodeEAN13Service;
    }

    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    public Optional<Orders> getOrderByCode(String orderCode) {
        return ordersRepository.findById(orderCode);
    }

    public Orders saveOrder(Orders order) {
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        String unitIDString = order.getUnitID().getUnitID().toString();

        String seqName = "HD/" + year + "/" + unitIDString;
        TbSYSSEQUENCE sequence = sysSequenceService.getSEQUENCE(seqName);

        if (sequence == null) {
            sequence = new TbSYSSEQUENCE();
            sequence.setSeqname(seqName);
            sequence.setSeqvalue(1);
            sysSequenceService.add(sequence);
        }

        String orderCode = year + unitIDString + String.format("%07d", sequence.getSeqvalue());
        String ean13Code = ordercodeEAN13Service.buildEAN13(orderCode);

        sequence.setSeqvalue(sequence.getSeqvalue() + 1);
        sysSequenceService.update(sequence);

        order.setOrderCode(ean13Code);
        // Đặt createdDate thành ngày giờ hiện tại
        order.setCreatedDate(Calendar.getInstance().getTime());

        // Đặt status thành "New Order"
        order.setStatus("New Order");

        // Đặt disabled thành "false"
        order.setDisabled(false);
        
        //set booked_Qty & shipped_qty = 0
        order.setBookQty(0.0);
        order.setShippedQty(0.0);

        return ordersRepository.save(order);
    }

    public Orders updateOrder(Orders updatedOrder) {
        Optional<Orders> existingOrder = ordersRepository.findById(updatedOrder.getOrderCode());

        if (existingOrder.isPresent()) {
            Orders currentOrder = existingOrder.get();

            // Cập nhật thông tin đơn hàng với thông tin từ updatedOrder
            currentOrder.setName(updatedOrder.getName());
            currentOrder.setGroupID(updatedOrder.getGroupID());
            currentOrder.setUnitID(updatedOrder.getUnitID());
            currentOrder.setAmount(updatedOrder.getAmount());
            currentOrder.setDescription(updatedOrder.getDescription());

            // Đặt createdDate thành ngày giờ hiện tại
            currentOrder.setCreatedDate(Calendar.getInstance().getTime());

            return ordersRepository.save(currentOrder); // Hibernate sẽ tự động cập nhật bản ghi
        } else {
            try {
                // Xử lý khi đơn hàng không tồn tại
                throw new NotFoundException("Không tìm thấy đơn hàng với mã " + updatedOrder.getOrderCode());
            } catch (NotFoundException ex) {
                Logger.getLogger(OrdersService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public Orders getOrderByOrderCode(String orderCode) {
        Optional<Orders> order = ordersRepository.findById(orderCode);
        return order.orElse(null);
    }

    public void confirmOrder(String orderCode) {
        // Tìm đơn hàng theo mã đơn hàng
        Optional<Orders> optionalOrder = ordersRepository.findById(orderCode);
        if (optionalOrder.isPresent()) {
            Orders order = optionalOrder.get();
            // Cập nhật trạng thái và disabled
            order.setStatus("Pending");
            ordersRepository.save(order);
        } else {
            try {
                throw new NotFoundException("Không tìm thấy đơn hàng với mã " + orderCode);
            } catch (NotFoundException ex) {
                Logger.getLogger(OrdersService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void completeOrder(String orderCode) {
        // Tìm đơn hàng theo mã đơn hàng
        Optional<Orders> optionalOrder = ordersRepository.findById(orderCode);
        if (optionalOrder.isPresent()) {
            Orders order = optionalOrder.get();
            // Cập nhật trạng thái và disabled
            order.setStatus("Complete");
            ordersRepository.save(order);
        } else {
            try {
                throw new NotFoundException("Không tìm thấy đơn hàng với mã " + orderCode);
            } catch (NotFoundException ex) {
                Logger.getLogger(OrdersService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void cancelOrder(String orderCode) {
        // Tìm đơn hàng theo mã đơn hàng
        Optional<Orders> optionalOrder = ordersRepository.findById(orderCode);
        if (optionalOrder.isPresent()) {
            Orders order = optionalOrder.get();
            // Cập nhật trạng thái và disabled
            order.setStatus("Cancelled Order");
            order.setDisabled(true);
            ordersRepository.save(order);
        } else {
            try {
                throw new NotFoundException("Không tìm thấy đơn hàng với mã " + orderCode);
            } catch (NotFoundException ex) {
                Logger.getLogger(OrdersService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
