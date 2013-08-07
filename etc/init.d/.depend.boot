TARGETS = mountkernfs.sh udev mountdevsubfs.sh bootlogd hostname.sh hwclockfirst.sh hdparm ifupdown ifupdown-clean mountall.sh mountoverflowtmp fuse mountnfs.sh mountnfs-bootclean.sh hwclock.sh checkroot.sh networking mdadm-raid checkfs.sh urandom fixudev lvm2 procps module-init-tools stop-bootlogd-single mountall-bootclean.sh screen-cleanup udev-mtab x11-common mtab.sh bootmisc.sh
INTERACTIVE = udev checkroot.sh checkfs.sh
udev: mountkernfs.sh
mountdevsubfs.sh: mountkernfs.sh udev
bootlogd: mountdevsubfs.sh
hostname.sh: bootlogd
hwclockfirst.sh: mountdevsubfs.sh bootlogd
hdparm: mountdevsubfs.sh udev bootlogd
ifupdown: ifupdown-clean
ifupdown-clean: checkroot.sh
mountall.sh: mdadm-raid lvm2 checkfs.sh
mountoverflowtmp: mountall-bootclean.sh
fuse: mountall.sh mountoverflowtmp mountnfs.sh mountnfs-bootclean.sh
mountnfs.sh: mountall.sh mountoverflowtmp networking ifupdown
mountnfs-bootclean.sh: mountall.sh mountoverflowtmp mountnfs.sh
hwclock.sh: checkroot.sh bootlogd
checkroot.sh: mountdevsubfs.sh hostname.sh hwclockfirst.sh hdparm bootlogd
networking: mountkernfs.sh mountall.sh mountoverflowtmp ifupdown
mdadm-raid: mountkernfs.sh hostname.sh udev
checkfs.sh: mdadm-raid lvm2 checkroot.sh mtab.sh
urandom: mountall.sh mountoverflowtmp
fixudev: mountall.sh mountoverflowtmp
lvm2: mountdevsubfs.sh udev mdadm-raid bootlogd
procps: mountkernfs.sh mountall.sh mountoverflowtmp udev module-init-tools bootlogd
module-init-tools: checkroot.sh
stop-bootlogd-single: mountall.sh mountoverflowtmp udev ifupdown ifupdown-clean fuse mountnfs.sh mountnfs-bootclean.sh hwclock.sh checkroot.sh networking mdadm-raid mountkernfs.sh hostname.sh checkfs.sh urandom fixudev lvm2 mountdevsubfs.sh procps module-init-tools mountall-bootclean.sh hwclockfirst.sh hdparm bootlogd screen-cleanup udev-mtab x11-common mtab.sh bootmisc.sh
mountall-bootclean.sh: mountall.sh
screen-cleanup: mountall.sh mountoverflowtmp mountnfs.sh mountnfs-bootclean.sh
udev-mtab: udev mountall.sh mountoverflowtmp
x11-common: mountall.sh mountoverflowtmp
mtab.sh: checkroot.sh
bootmisc.sh: mountall.sh mountoverflowtmp mountnfs.sh mountnfs-bootclean.sh udev
