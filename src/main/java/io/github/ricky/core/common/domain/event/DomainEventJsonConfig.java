package io.github.ricky.core.common.domain.event;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.lang.annotation.*;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/5
 * @className DomainEventJsonConfig
 * @desc 领域事件序列化配置注解
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type", visible = true)
// @JsonSubTypes(value = {
//         @JsonSubTypes.Type(value = AppAttributesCreatedEvent.class, name = "ATTRIBUTES_CREATED"),
//         @JsonSubTypes.Type(value = AppAttributesDeletedEvent.class, name = "ATTRIBUTES_DELETED"),
//         @JsonSubTypes.Type(value = AppControlOptionsDeletedEvent.class, name = "CONTROL_OPTIONS_DELETED"),
//         @JsonSubTypes.Type(value = AppControlsDeletedEvent.class, name = "CONTROLS_DELETED"),
//         @JsonSubTypes.Type(value = AppCreatedEvent.class, name = "APP_CREATED"),
//         @JsonSubTypes.Type(value = AppCreatedFromTemplateEvent.class, name = "APP_CREATED_FROM_TEMPLATE"),
//         @JsonSubTypes.Type(value = AppDeletedEvent.class, name = "APP_DELETED"),
//         @JsonSubTypes.Type(value = AppPagesDeletedEvent.class, name = "PAGES_DELETED"),
//         @JsonSubTypes.Type(value = GroupCreatedEvent.class, name = "GROUP_CREATED"),
//         @JsonSubTypes.Type(value = GroupDeactivatedEvent.class, name = "GROUP_DEACTIVATED"),
//         @JsonSubTypes.Type(value = GroupActivatedEvent.class, name = "GROUP_ACTIVATED"),
//         @JsonSubTypes.Type(value = GroupDeletedEvent.class, name = "GROUP_DELETED"),
//         @JsonSubTypes.Type(value = GroupManagersChangedEvent.class, name = "GROUP_MANAGERS_CHANGED"),
//         @JsonSubTypes.Type(value = MemberCreatedEvent.class, name = "MEMBER_CREATED"),
//         @JsonSubTypes.Type(value = MemberDeletedEvent.class, name = "MEMBER_DELETED"),
//         @JsonSubTypes.Type(value = MemberNameChangedEvent.class, name = "MEMBER_NAME_CHANGED"),
//         @JsonSubTypes.Type(value = MemberDepartmentsChangedEvent.class, name = "MEMBER_DEPARTMENTS_CHANGED"),
//         @JsonSubTypes.Type(value = PageChangedToSubmitPerInstanceEvent.class, name = "PAGE_CHANGED_TO_SUBMIT_PER_INSTANCE"),
//         @JsonSubTypes.Type(value = PageChangedToSubmitPerMemberEvent.class, name = "PAGE_CHANGED_TO_SUBMIT_PER_MEMBER"),
//         @JsonSubTypes.Type(value = PlateBatchCreatedEvent.class, name = "PLATE_BATCH_CREATED"),
//         @JsonSubTypes.Type(value = PlateBatchDeletedEvent.class, name = "PLATE_BATCH_DELETED"),
//         @JsonSubTypes.Type(value = PlateBoundEvent.class, name = "PLATE_BOUND"),
//         @JsonSubTypes.Type(value = PlateUnboundEvent.class, name = "PLATE_UNBOUND"),
//         @JsonSubTypes.Type(value = QrBaseSettingUpdatedEvent.class, name = "QR_BASE_SETTING_UPDATED"),
//         @JsonSubTypes.Type(value = QrCreatedEvent.class, name = "QR_CREATED"),
//         @JsonSubTypes.Type(value = QrDeletedEvent.class, name = "QR_DELETED"),
//         @JsonSubTypes.Type(value = QrGroupChangedEvent.class, name = "QR_GROUP_CHANGED"),
//         @JsonSubTypes.Type(value = QrMarkedAsTemplateEvent.class, name = "QR_MARKED_AS_TEMPLATE"),
//         @JsonSubTypes.Type(value = QrPlateResetEvent.class, name = "QR_PLATE_RESET"),
//         @JsonSubTypes.Type(value = QrRenamedEvent.class, name = "QR_RENAMED"),
//         @JsonSubTypes.Type(value = SubmissionCreatedEvent.class, name = "SUBMISSION_CREATED"),
//         @JsonSubTypes.Type(value = SubmissionDeletedEvent.class, name = "SUBMISSION_DELETED"),
//         @JsonSubTypes.Type(value = SubmissionUpdatedEvent.class, name = "SUBMISSION_UPDATED"),
//         @JsonSubTypes.Type(value = SubmissionApprovedEvent.class, name = "SUBMISSION_APPROVED"),
//         @JsonSubTypes.Type(value = TenantCreatedEvent.class, name = "TENANT_CREATED"),
//         @JsonSubTypes.Type(value = TenantSubdomainUpdatedEvent.class, name = "TENANT_SUBDOMAIN_UPDATED"),
//         @JsonSubTypes.Type(value = TenantActivatedEvent.class, name = "TENANT_ACTIVATED"),
//         @JsonSubTypes.Type(value = TenantDeactivatedEvent.class, name = "TENANT_DEACTIVATED"),
//         @JsonSubTypes.Type(value = TenantBaseSettingUpdatedEvent.class, name = "TENANT_BASE_SETTING_UPDATED"),
//         @JsonSubTypes.Type(value = TenantInvoiceTitleUpdatedEvent.class, name = "TENANT_INVOICE_TITLE_UPDATED"),
//         @JsonSubTypes.Type(value = TenantOrderAppliedEvent.class, name = "TENANT_ORDER_APPLIED"),
//         @JsonSubTypes.Type(value = TenantPlanUpdatedEvent.class, name = "TENANT_PLAN_UPDATED"),
//         @JsonSubTypes.Type(value = TenantSubdomainReadyStatusUpdatedEvent.class, name = "TENANT_SUBDOMAIN_READY_STATUS_UPDATED"),
//         @JsonSubTypes.Type(value = TenantResourceUsageUpdatedEvent.class, name = "TENANT_RESOURCE_USAGE_UPDATED"),
//         @JsonSubTypes.Type(value = QrCustomIdUpdatedEvent.class, name = "QR_CUSTOM_ID_UPDATED"),
//         @JsonSubTypes.Type(value = QrUnMarkedAsTemplateEvent.class, name = "QR_UNMARKED_AS_TEMPLATE"),
//         @JsonSubTypes.Type(value = QrAttributesUpdatedEvent.class, name = "QR_ATTRIBUTES_UPDATED"),
//         @JsonSubTypes.Type(value = QrDescriptionUpdatedEvent.class, name = "QR_DESCRIPTION_UPDATED"),
//         @JsonSubTypes.Type(value = QrGeolocationUpdatedEvent.class, name = "QR_GEOLOCATION_UPDATED"),
//         @JsonSubTypes.Type(value = QrHeaderImageUpdatedEvent.class, name = "QR_HEADER_IMAGE_UPDATED"),
//         @JsonSubTypes.Type(value = QrActivatedEvent.class, name = "QR_ACTIVATED"),
//         @JsonSubTypes.Type(value = QrDeactivatedEvent.class, name = "QR_DEACTIVATED"),
//         @JsonSubTypes.Type(value = QrCirculationStatusChangedEvent.class, name = "QR_CIRCULATION_STATUS_CHANGED"),
//         @JsonSubTypes.Type(value = OrderCreatedEvent.class, name = "ORDER_CREATED"),
//         @JsonSubTypes.Type(value = OrderBankTransferUpdatedEvent.class, name = "ORDER_BANK_TRANSFER_UPDATED"),
//         @JsonSubTypes.Type(value = OrderWxTransferUpdatedEvent.class, name = "ORDER_WX_TRANSFER_UPDATED"),
//         @JsonSubTypes.Type(value = OrderDeliveryUpdatedEvent.class, name = "ORDER_DELIVERY_UPDATED"),
//         @JsonSubTypes.Type(value = OrderInvoiceIssuedEvent.class, name = "ORDER_INVOICE_ISSUED"),
//         @JsonSubTypes.Type(value = OrderInvoiceRequestedEvent.class, name = "ORDER_INVOICE_REQUESTED"),
//         @JsonSubTypes.Type(value = OrderRefundUpdatedEvent.class, name = "ORDER_REFUND_UPDATED"),
//         @JsonSubTypes.Type(value = OrderWxPayUpdatedEvent.class, name = "ORDER_WX_PAY_UPDATED"),
//         @JsonSubTypes.Type(value = AssignmentPlanDeletedEvent.class, name = "ASSIGNMENT_PLAN_DELETED"),
//         @JsonSubTypes.Type(value = DepartmentCreatedEvent.class, name = "DEPARTMENT_CREATED"),
//         @JsonSubTypes.Type(value = DepartmentDeletedEvent.class, name = "DEPARTMENT_DELETED"),
//         @JsonSubTypes.Type(value = DepartmentManagersChangedEvent.class, name = "DEPARTMENT_MANAGERS_CHANGED"),
//         @JsonSubTypes.Type(value = DepartmentRenamedEvent.class, name = "DEPARTMENT_RENAMED"),
//         @JsonSubTypes.Type(value = MemberAddedToDepartmentEvent.class, name = "MEMBER_ADDED_TO_DEPARTMENT"),
//         @JsonSubTypes.Type(value = MemberRemovedFromDepartmentEvent.class, name = "MEMBER_REMOVED_FROM_DEPARTMENT"),
//         @JsonSubTypes.Type(value = GroupSyncEnabledEvent.class, name = "GROUP_SYNC_ENABLED"),
//         @JsonSubTypes.Type(value = DepartmentHierarchyChangedEvent.class, name = "DEPARTMENT_HIERARCHY_CHANGED"),
//         @JsonSubTypes.Type(value = AssignmentCreatedEvent.class, name = "ASSIGNMENT_CREATED"),
// })
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface DomainEventJsonConfig {
}
