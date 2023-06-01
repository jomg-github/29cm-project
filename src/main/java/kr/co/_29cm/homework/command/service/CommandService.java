package kr.co._29cm.homework.command.service;

import jakarta.validation.Valid;
import kr.co._29cm.homework.command.dto.InitialCommand;
import kr.co._29cm.homework.command.dto.OrderCommand;
import kr.co._29cm.homework.global.exception.NoSuchDataException;
import kr.co._29cm.homework.global.formatter.MoneyFormatter;
import kr.co._29cm.homework.order.dto.CreateOrderDTO;
import kr.co._29cm.homework.order.dto.OrderDTO;
import kr.co._29cm.homework.order.dto.OrderProductDTO;
import kr.co._29cm.homework.order.dto.RequestOrderProductDTO;
import kr.co._29cm.homework.order.service.OrderProductService;
import kr.co._29cm.homework.order.service.OrderService;
import kr.co._29cm.homework.product.dto.ProductDTO;
import kr.co._29cm.homework.product.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommandService {
    private final ProductService productService;
    private final OrderService orderService;
    private final OrderProductService orderProductService;
    private final BufferedReader br;
    private final InitialCommand initialCommand;
    private final OrderCommand orderCommand;

    public CommandService(ProductService productService, OrderService orderService, OrderProductService orderProductService) {
        this.productService = productService;
        this.orderService = orderService;
        this.orderProductService = orderProductService;

        this.br = new BufferedReader(new InputStreamReader(System.in));
        this.initialCommand = new InitialCommand();
        this.orderCommand = new OrderCommand();
    }

    public void process() throws IOException {
        inputInitialCommand();
        if (initialCommand.getCommand() == null) {
            return;
        }

        while(!initialCommand.isTerminated()) {
            showAllProducts();
            makeOrder();
            inputInitialCommand();
        }
        System.out.println("고객님의 주문 감사합니다.");
    }

    private void inputInitialCommand() throws IOException {
        System.out.println("입력(o[order]: 주문, q[quit]: 종료)");
        initialCommand.input(br.readLine());
    }

    private void inputProductOrderCommand() throws IOException {
        System.out.println("상품번호: ");
        orderCommand.inputProductId(br.readLine());
        System.out.println("수량: ");
        orderCommand.inputOrderProductQuantityCommand(br.readLine());
    }

    @Transactional
    void makeOrder() throws IOException {
        List<RequestOrderProductDTO> requestOrderProductDTOList = new ArrayList<>();

        inputProductOrderCommand();
        while(!orderCommand.isTerminated()) {
            requestOrderProductDTOList.add(RequestOrderProductDTO.builder()
                    .productId(orderCommand.getProductId())
                    .orderProductQuantity(orderCommand.getOrderProductQuantity())
                    .build());

            inputProductOrderCommand();
        }

        Long orderId = orderService.createOrder(CreateOrderDTO.builder()
                .requestOrderProductDTOList(requestOrderProductDTOList)
                .build()
        );

        OrderDTO orderDTO = orderService.getOrder(orderId);
        List<OrderProductDTO> orderProductDTOList = orderDTO.getOrderProductDTOList();
        List<ProductDTO> productDTOList = productService.getProducts(orderProductDTOList.stream().map(OrderProductDTO::getProductId).toList());

        showOrderResult(orderDTO, orderProductDTOList, productDTOList);
    }

    private void showOrderResult(OrderDTO orderDTO, List<OrderProductDTO> orderProductDTOList, List<ProductDTO> productDTOList) {
        // 주문내역
        System.out.println("주문 내역: ");
        System.out.println("------------------------------");

        // 주문상품 정보
        StringBuilder sb = new StringBuilder();
        for (OrderProductDTO orderProductDTO: orderProductDTOList) {
            sb.append(productDTOList.stream()
                    .filter(productDTO -> productDTO.getProductId().equals(orderProductDTO.getProductId()))
                    .findAny()
                    .orElseThrow(NoSuchDataException::new)
                    .getProductName()
            );
            sb.append(" - ");
            sb.append(orderProductDTO.getOrderProductQuantity());
            sb.append("개\n");
        }
        sb.append("------------------------------\n");

        // 주문금액 정보
        sb.append("주문금액: ");
        sb.append(MoneyFormatter.won(orderDTO.getOrderTotalAmount()));
        sb.append("\n");
        if (!orderDTO.isFreeShipping()) {
            sb.append("배송비: ");
            sb.append(MoneyFormatter.won(orderDTO.getShippingFee()));
            sb.append("\n");
        }
        sb.append("------------------------------\n");

        // 지불금액 정보
        sb.append("지불금액: ");
        sb.append(MoneyFormatter.won(orderDTO.getOrderTotalAmount() + orderDTO.getShippingFee()));
        System.out.println(sb);
    }

    private void showAllProducts() {
        List<ProductDTO> allProducts = productService.getAllProducts();
        StringBuilder sb = new StringBuilder();
        sb.append("상품번호\t상품명\t판매가격\t재고수\n");

        for(ProductDTO productDto: allProducts) {
            sb.append(productDto.getProductId());
            sb.append("\t");
            sb.append(productDto.getProductName());
            sb.append("\t");
            sb.append(productDto.getProductPrice());
            sb.append("\t");
            sb.append(productDto.getProductQuantity());
            sb.append("\n");
        }
        System.out.println(sb);
    }

}

