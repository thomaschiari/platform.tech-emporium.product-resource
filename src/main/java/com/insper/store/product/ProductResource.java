package com.insper.store.product;

import java.util.Map;
import java.util.stream.Collectors;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.util.List;

@RestController
public class ProductResource implements ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products/info")
    public ResponseEntity<Map<String, String>> info() {
        // Assuming ProductApplication.class is correctly referenced
        String jarPath;
        try {
            jarPath = URLDecoder.decode(
                    ProductApplication.class.getProtectionDomain()
                        .getCodeSource()
                        .getLocation()
                        .getPath(),
                    "UTF-8");
        } catch (UnsupportedEncodingException e) {
            jarPath = "Error decoding path";
        }

        return ResponseEntity.ok(
            Map.ofEntries(
                Map.entry("microservice.name", ProductApplication.class.getSimpleName()),
                Map.entry("os.arch", System.getProperty("os.arch")),
                Map.entry("os.name", System.getProperty("os.name")),
                Map.entry("os.version", System.getProperty("os.version")),
                Map.entry("file.separator", System.getProperty("file.separator")),
                Map.entry("java.class.path", System.getProperty("java.class.path")),
                Map.entry("java.home", System.getProperty("java.home")),
                Map.entry("java.vendor", System.getProperty("java.vendor")),
                Map.entry("java.vendor.url", System.getProperty("java.vendor.url")),
                Map.entry("java.version", System.getProperty("java.version")),
                Map.entry("line.separator", System.getProperty("line.separator").replaceAll("\r\n|\r|\n", " ")),
                Map.entry("path.separator", System.getProperty("path.separator")),
                Map.entry("user.dir", System.getProperty("user.dir")),
                Map.entry("user.home", System.getProperty("user.home")),
                Map.entry("user.name", System.getProperty("user.name")),
                Map.entry("jar", jarPath)
            )
        );
    }

    @Override
    public ResponseEntity<ProductOut> create(ProductIn in) {
        ProductModel model = productService.create(in);
        return ResponseEntity.created(
            ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(model.id()) // Assuming fluent accessors are correctly used here.
                .toUri()
        ).body(model.toOut()); // Use the conversion method provided in ProductModel.
    }

    @Override
    public ResponseEntity<ProductOut> update(Integer id, ProductIn in) {
        ProductModel model = productService.update(id, in);
        return ResponseEntity.ok(model.toOut());
    }

    @Override
    public ResponseEntity<ProductOut> read(Integer id) {
        ProductModel model = productService.read(id);
        return ResponseEntity.ok(model.toOut());
    }

    @Override
    @GetMapping("/products")
    public ResponseEntity<List<ProductOut>> readAll() {
        List<ProductModel> products = productService.readAll(); // Note the change here to call the correct method
        List<ProductOut> productOuts = products.stream()
                                               .map(product -> product.toOut()) // Assuming `toOut()` is a method in `ProductModel`
                                               .collect(Collectors.toList());
        return ResponseEntity.ok(productOuts);
    }
}