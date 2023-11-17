package com.cleverpine.template.controller;

import com.cleverpine.template.api.TemplatesApi;
import com.cleverpine.template.auth.ViravaSecured;
import com.cleverpine.template.auth.roles.Resources;
import com.cleverpine.template.model.Template;
import com.cleverpine.template.model.TemplateFull;
import com.cleverpine.template.model.TemplateListResponse;
import com.cleverpine.template.model.TemplateResponse;
import com.cleverpine.viravaspringhelper.dto.ScopeType;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TemplateController implements TemplatesApi {

    private static final Map<Integer, TemplateFull> templateList = new HashMap<>();
    private static int index = 1;

    @Override
    @ViravaSecured(resource = Resources.TEMPLATE, scope = ScopeType.READ)
    public ResponseEntity<TemplateResponse> getTemplate(Integer id) {
        var response = new TemplateResponse();
        response.data(templateList.get(id));
        return ResponseEntity.ok(response);
    }

    @Override
    @ViravaSecured(resource = Resources.TEMPLATE, scope = ScopeType.READ)
    public ResponseEntity<TemplateListResponse> getTemplates() {
        var response = new TemplateListResponse();
        response.data(new LinkedList<>(templateList.values()));
        return ResponseEntity.ok(response);
    }

    @Override
    @ViravaSecured(resource = Resources.TEMPLATE, scope = ScopeType.UPDATE)
    public ResponseEntity<TemplateResponse> updateTemplate(Integer id, TemplateFull template) {
        templateList.put(id, template);
        var response = new TemplateResponse();
        response.data(template);
        return ResponseEntity.ok(response);
    }

    @Override
    @ViravaSecured(resource = Resources.TEMPLATE, scope = ScopeType.CREATE)
    public ResponseEntity<TemplateResponse> createTemplate(Template template) {
        var templateFull = new TemplateFull();
        templateFull.setBar(template.getBar());
        templateFull.setFoo(template.getFoo());
        templateFull.setId(index++);
        templateList.put(templateFull.getId(), templateFull);
        var response = new TemplateResponse();
        response.data(templateFull);
        return ResponseEntity.ok(response);
    }

    @Override
    @ViravaSecured(resource = Resources.TEMPLATE, scope = ScopeType.DELETE)
    public ResponseEntity<Void> deleteTemplate(Integer id) {
        return ResponseEntity.noContent().build();
    }

}
