package com.cleverpine.template.controller;

import com.cleverpine.cpspringerrorutil.util.ListResponseEntityUtil;
import com.cleverpine.cpspringerrorutil.util.ResponseEntityUtil;
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
    private final ResponseEntityUtil<TemplateResponse, TemplateFull> responseEntityUtil;
    private final ListResponseEntityUtil<TemplateListResponse, TemplateFull> listResponseEntityUtil;

    @Override
    @ViravaSecured(resource = Resources.TEMPLATE, scope = ScopeType.READ)
    public ResponseEntity<TemplateResponse> getTemplate(Integer id) {
        return responseEntityUtil.ok(templateList.get(id));
    }

    @Override
    @ViravaSecured(resource = Resources.TEMPLATE, scope = ScopeType.READ)
    public ResponseEntity<TemplateListResponse> getTemplates() {
        return listResponseEntityUtil.ok(new LinkedList<>(templateList.values()));
    }

    @Override
    @ViravaSecured(resource = Resources.TEMPLATE, scope = ScopeType.UPDATE)
    public ResponseEntity<TemplateResponse> updateTemplate(Integer id, TemplateFull template) {
        templateList.put(template.getId(), template);
        return responseEntityUtil.ok(template);
    }

    @Override
    @ViravaSecured(resource = Resources.TEMPLATE, scope = ScopeType.CREATE)
    public ResponseEntity<TemplateResponse> createTemplate(Template template) {
        var templateFull = new TemplateFull();
        templateFull.setBar(template.getBar());
        templateFull.setFoo(template.getFoo());
        templateFull.setId(index++);
        templateList.put(templateFull.getId(), templateFull);
        return responseEntityUtil.created(templateFull);
    }

    @Override
    @ViravaSecured(resource = Resources.TEMPLATE, scope = ScopeType.DELETE)
    public ResponseEntity<Void> deleteTemplate(Integer id) {
        return responseEntityUtil.noContent();
    }

}
