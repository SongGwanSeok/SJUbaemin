package SJU.SJUbaemin.Service;

import SJU.SJUbaemin.Domain.Dto.Product.ProductEnrollRequestDto;
import SJU.SJUbaemin.Domain.Dto.Product.ProductEnrollResponseDto;
import SJU.SJUbaemin.Domain.Entity.Product.Product;
import SJU.SJUbaemin.Domain.Entity.Product.ProductContent;
import SJU.SJUbaemin.Domain.Entity.Product.ProductType;
import SJU.SJUbaemin.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
//    private final String uploadPath = "src/main/resources/static/images/";


    /**
     * 상품 등록 (base64 방식)
     */
    @Transactional
    public ProductEnrollResponseDto enroll(ProductEnrollRequestDto productEnrollRequestDto) {
        validationDuplicateProductNameEnroll(productEnrollRequestDto);

        Product product = productDtoToEntity(productEnrollRequestDto);

        for (String content : productEnrollRequestDto.getContent()) {
            ProductContent productContent = new ProductContent(content, product);
            product.addContent(productContent);
        }

        Product savedProduct = productRepository.save(product);
        Product findProduct = productRepository.findOne(savedProduct.getId());

        return productEntityToDto(findProduct);

    }

    /**
     * product EntityToDto, DtoToEntity
     */
    private static ProductEnrollResponseDto productEntityToDto(Product findProduct)  {
        List<String> content = findProduct.getContent().stream()
                .map(c -> new String(c.getContent(), StandardCharsets.UTF_8)).collect(Collectors.toList());

        return ProductEnrollResponseDto.builder()
                .id(findProduct.getId())
                .image(new String(findProduct.getImage(), StandardCharsets.UTF_8))
                .type(findProduct.getType())
                .name(findProduct.getName())
                .price(findProduct.getPrice())
                .content(content)
                .build();
    }

    private Product productDtoToEntity(ProductEnrollRequestDto productEnrollRequestDto) {

        List<ProductContent> list = new ArrayList<>();
        return Product.builder()
                .image(productEnrollRequestDto.getImage().getBytes(StandardCharsets.UTF_8))
                .type(productEnrollRequestDto.getType())
                .name(productEnrollRequestDto.getName())
                .price(productEnrollRequestDto.getPrice())
                .content(list)
                .build();
    }

    /**
     * 상품등록 (이미지 파일) 실패 ㅠㅠ
     */
    /*

    @Transactional
    public Product register(ProductRequestDto productRequestDto, List<MultipartFile> multipartFiles) throws IOException {
        //상품 이름 겹치는 경우 삭제
        validationDuplicateProductName(productRequestDto);

        List<ProductImage> productImages = new ArrayList<>();
        Product product = Product.builder()
                .name(productRequestDto.getName())
                .price(productRequestDto.getPrice())
                .quantity(productRequestDto.getQuantity())
                .content(productRequestDto.getContent())
                .type(productRequestDto.getType())
                .build();

        if (multipartFiles.isEmpty()) {
            return productRepository.save(product);
        }

        // 프로젝트 폴더에 저장하기 위해 절대경로를 설정 (Window 의 Tomcat 은 Temp 파일을 이용한다)
        String absolutePath = new File("").getAbsolutePath() + "/";

        String path = "src/main/resources/static/images/" + product.getName();
        File file = new File(path);
        if (!file.exists()) {
            // mkdir() 함수와 다른 점은 상위 디렉토리가 존재하지 않을 때 그것까지 생성
            file.mkdirs();
        }

        for (MultipartFile multipartFile : multipartFiles) {
            if(!multipartFile.isEmpty()) {
                String contentType = multipartFile.getContentType();
                String originalFileExtension;
                if(ObjectUtils.isEmpty(contentType)) {
                    break;
                } else {
                    if (contentType.contains("image/jpeg")) {
                        originalFileExtension = ".jpg";
                    } else if (contentType.contains("image/png")) {
                        originalFileExtension = ".png";
                    } else if (contentType.contains("image/gif")) {
                        originalFileExtension = ".gif";
                    }
                    // 다른 파일 명이면 아무 일 하지 않는다
                    else {
                        break;
                    }
                }
                // 각 이름은 겹치면 안되므로 나노 초까지 동원하여 지정
                String new_file_name = Long.toString(System.nanoTime()) + originalFileExtension;
                file = new File(absolutePath + path + "/" + new_file_name);
                multipartFile.transferTo(file);

                ProductImage productImage = ProductImage.builder()
                        .type(contentType)
                        .date(LocalDate.now())
                        .path(path + "/" + new_file_name)
                        .name(new_file_name)
                        .build();
                productImages.add(productImage);
            }
        }
        product.addImages(productImages);

        Product savedProduct = productDtoToEntity(product);

        return savedProduct;
    }

     */

    /**
     * 상품 삭제
     * check box로 상품의 id 번호를 가져와서 삭제
     */
    @Transactional
    public void deregister(Long productId) {
        Product product = productRepository.findOne(productId);

        /*
        // 프로젝트 폴더에 저장하기 위해 절대경로를 설정 (Window 의 Tomcat 은 Temp 파일을 이용한다)
        String absolutePath = new File("").getAbsolutePath() + "/";
        String path = "src/main/resources/static/images/";
        String productFileName = product.getName().toString();
        File folder = new File(absolutePath + path + productFileName);

        //해당 폴더 삭제
        try {
            File[] files = folder.listFiles();
            for(File file : files){
                file.delete(); // 하위 파일 삭제
                log.info("파일 {}를 삭제했습니다.", file.getName());
            }
            folder.delete(); // 대상폴더 삭제
            log.info("폴더 {}를 삭제했습니다.", folder.getName());

        }catch (Exception e) {
            e.getStackTrace();
        }

         */

        productRepository.delete(product);
    }

    /**
     * 상품 찾기
     */
    public Product findOne(Long productId) {
        return productRepository.findOne(productId);
    }

    public List<Product> findProductsAll() {
        return productRepository.findAll();
    }

    public List<Product> findProductsCategory(ProductType type) {
        return productRepository.findByCategory(type);
    }



    /**
     * 상품 수정
     */
    @Transactional
    public void update (Long productId, ProductEnrollRequestDto productDto) {
        Product findProduct = productRepository.findOne(productId);
        findProduct.change(productDto);
    }
    

    //product가 있는지 확인
    /*
    private void validationDuplicateProductName(ProductRequestDto productRequestDto) {
        List<Product> products = productRepository.findByName(productRequestDto.getName());
        if (!products.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 상품입니다."); // 예외처리
        }
    }
    */

    private void validationDuplicateProductNameEnroll(ProductEnrollRequestDto productRequestDto) {
        List<Product> products = productRepository.findByName(productRequestDto.getName());
        if (!products.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 상품입니다."); // 예외처리
        }
    }


}
