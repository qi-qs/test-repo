const PERMISSION_GROUPS = 'permissionGroups';
const PERMISSIONS = 'permissions';
const SELECTED_PERMISSIONS = 'selectedPermissions';

const mergePermissions = (id, newValue = []) => {
  const ownedPermissions = id === PERMISSIONS ? newValue : formApi.getValue(PERMISSIONS) || [];
  const groups = id === PERMISSION_GROUPS ? newValue : formApi.getValue(PERMISSION_GROUPS) || [];
  const groupPermissions = groups.flatMap((group) => group.permissions);
  return ownedPermissions.concat(groupPermissions);
};

formApi.on('fieldValueChange', (id, newValue = []) => {
  if (id === PERMISSIONS || id === PERMISSION_GROUPS) {
    formApi.setValue(SELECTED_PERMISSIONS, mergePermissions(id, newValue));
  }
});

formApi.on('ready', () => {
  formApi.setValue(SELECTED_PERMISSIONS, mergePermissions());
});
