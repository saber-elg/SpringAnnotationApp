package com.ensah.AnnotationApp.service;
import com.ensah.AnnotationApp.entity.Dataset;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface IDatasetService {
    Dataset createDataset(String nomDataset, String description, String classes, MultipartFile file);
    List<Dataset> getAllDatasets();
    void affecterAnnotateursAuDataset(Integer datasetId, List<Integer> annotateurIds);
    Dataset getDatasetById(Integer id);
}
