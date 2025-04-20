INSERT INTO loaiban (maLoaiBan, tenLoaiBan) VALUES ('LB001', 'Thường');
INSERT INTO loaiban (maLoaiBan, tenLoaiBan) VALUES ('LB002', 'VIP');
INSERT INTO ban (maBan, tenBan, trangThai, viTri, maLoaiBan) VALUES ('T1001', 1, 0, 'Tầng 1', 'LB001');
INSERT INTO ban (maBan, tenBan, trangThai, viTri, maLoaiBan) VALUES ('T1002', 2, 0, 'Tầng 1', 'LB001');
INSERT INTO ban (maBan, tenBan, trangThai, viTri, maLoaiBan) VALUES ('T1003', 3, 0, 'Tầng 1', 'LB001');
INSERT INTO ban (maBan, tenBan, trangThai, viTri, maLoaiBan) VALUES ('T1004', 4, 0, 'Tầng 1', 'LB001');
INSERT INTO ban (maBan, tenBan, trangThai, viTri, maLoaiBan) VALUES ('T1005', 5, 0, 'Tầng 1', 'LB001');
INSERT INTO ban (maBan, tenBan, trangThai, viTri, maLoaiBan) VALUES ('T1006', 6, 0, 'Tầng 1', 'LB001');
INSERT INTO ban (maBan, tenBan, trangThai, viTri, maLoaiBan) VALUES ('T1007', 7, 0, 'Tầng 1', 'LB001');
INSERT INTO ban (maBan, tenBan, trangThai, viTri, maLoaiBan) VALUES ('T1008', 8, 0, 'Tầng 1', 'LB001');
INSERT INTO ban (maBan, tenBan, trangThai, viTri, maLoaiBan) VALUES ('T1009', 9, 0, 'Tầng 1', 'LB001');
INSERT INTO ban (maBan, tenBan, trangThai, viTri, maLoaiBan) VALUES ('T1010', 10, 0, 'Tầng 1', 'LB001');
INSERT INTO ban (maBan, tenBan, trangThai, viTri, maLoaiBan) VALUES ('T2001', 1, 0, 'Tầng 2', 'LB002');
INSERT INTO ban (maBan, tenBan, trangThai, viTri, maLoaiBan) VALUES ('T2002', 2, 0, 'Tầng 2', 'LB002');
INSERT INTO ban (maBan, tenBan, trangThai, viTri, maLoaiBan) VALUES ('T2003', 3, 0, 'Tầng 2', 'LB002');
INSERT INTO ban (maBan, tenBan, trangThai, viTri, maLoaiBan) VALUES ('T2004', 4, 0, 'Tầng 2', 'LB001');
INSERT INTO ban (maBan, tenBan, trangThai, viTri, maLoaiBan) VALUES ('T2005', 5, 0, 'Tầng 2', 'LB001');
INSERT INTO ban (maBan, tenBan, trangThai, viTri, maLoaiBan) VALUES ('T2006', 6, 0, 'Tầng 2', 'LB001');
INSERT INTO ban (maBan, tenBan, trangThai, viTri, maLoaiBan) VALUES ('T2007', 7, 0, 'Tầng 2', 'LB001');
INSERT INTO ban (maBan, tenBan, trangThai, viTri, maLoaiBan) VALUES ('T2008', 8, 0, 'Tầng 2', 'LB001');
INSERT INTO ban (maBan, tenBan, trangThai, viTri, maLoaiBan) VALUES ('T2009', 9, 0, 'Tầng 2', 'LB001');
INSERT INTO ban (maBan, tenBan, trangThai, viTri, maLoaiBan) VALUES ('T2010', 10, 0, 'Tầng 2', 'LB002');
INSERT INTO ban (maBan, tenBan, trangThai, viTri, maLoaiBan) VALUES ('T3001', 1, 0, 'Tầng 3', 'LB002');
INSERT INTO ban (maBan, tenBan, trangThai, viTri, maLoaiBan) VALUES ('T3002', 2, 0, 'Tầng 3', 'LB002');
INSERT INTO ban (maBan, tenBan, trangThai, viTri, maLoaiBan) VALUES ('T3003', 3, 0, 'Tầng 3', 'LB002');
INSERT INTO ban (maBan, tenBan, trangThai, viTri, maLoaiBan) VALUES ('T3004', 4, 0, 'Tầng 3', 'LB001');
INSERT INTO ban (maBan, tenBan, trangThai, viTri, maLoaiBan) VALUES ('T3005', 5, 0, 'Tầng 3', 'LB001');



INSERT into loaimonan (maLoaiMon, tenLoaiMon) VALUES (N'LM001', N'Dimsum');
INSERT into loaimonan (maLoaiMon, tenLoaiMon) VALUES (N'LM002', N'Drink');
INSERT into loaimonan (maLoaiMon, tenLoaiMon) VALUES (N'LM003', N'Soup');
INSERT into loaimonan (maLoaiMon, tenLoaiMon) VALUES (N'LM004', N'Vịt Bắc Kinh');
INSERT into loaimonan (maLoaiMon, tenLoaiMon) VALUES (N'LM005', N'Meat');
INSERT into loaimonan (maLoaiMon, tenLoaiMon) VALUES (N'LM006', N'Sea Food');
INSERT into loaimonan (maLoaiMon, tenLoaiMon) VALUES (N'LM007', N'Tofu');
INSERT into loaimonan (maLoaiMon, tenLoaiMon) VALUES (N'LM008', N'Rice');
INSERT into loaimonan (maLoaiMon, tenLoaiMon) VALUES (N'LM009', N'Noodle');
INSERT into loaimonan (maLoaiMon, tenLoaiMon) VALUES (N'LM010', N'Vegetable');
INSERT into loaimonan (maLoaiMon, tenLoaiMon) VALUES (N'LM011', N'Dessert');








INSERT INTO monan (maMonAn, tenMonAn, urlhinhAnh, gia, trangThai, maLoaiMon,maKM) VALUES (N'MA001', N'Mì khô xá xíu', N'/hinhAnh/miKhoXaXiu.jpg', 105000, 1, N'LM009', NULL);
INSERT INTO monan (maMonAn, tenMonAn, urlhinhAnh, gia, trangThai, maLoaiMon,maKM) VALUES (N'MA002', N'Mì sủi cảo', N'/hinhAnh/miSuiCao.jpg', 115000, 1, N'LM009', NULL);
INSERT INTO monan (maMonAn, tenMonAn, urlhinhAnh, gia, trangThai, maLoaiMon,maKM) VALUES (N'MA003', N'Mì xào vịt', N'/hinhAnh/miXaoVit.jpg', 115000, 1, N'LM009', NULL);
INSERT INTO monan (maMonAn, tenMonAn, urlhinhAnh, gia, trangThai, maLoaiMon,maKM) VALUES (N'MA004', N'Canh bào ngư', N'/hinhAnh/canhBaoNgu.jpg', 155000, 1, N'LM003', NULL);
INSERT INTO monan (maMonAn, tenMonAn, urlhinhAnh, gia, trangThai, maLoaiMon,maKM) VALUES (N'MA005', N'Canh vi cá thịt cua', N'/hinhAnh/canhViCaThitCua.jpg', 205000, 1, N'LM003', NULL);
INSERT INTO monan (maMonAn, tenMonAn, urlhinhAnh, gia, trangThai, maLoaiMon,maKM) VALUES (N'MA006', N'Canh xương hầm ', N'/hinhAnh/canhXuongHam.jpg', 175000, 1, N'LM003', NULL);
INSERT INTO monan (maMonAn, tenMonAn, urlhinhAnh, gia, trangThai, maLoaiMon,maKM) VALUES (N'MA007', N'Canh tiềm Atiso', N'/hinhAnh/canhTiemAtiso.jpg', 255000, 1, N'LM003', NULL);
INSERT INTO monan (maMonAn, tenMonAn, urlhinhAnh, gia, trangThai, maLoaiMon,maKM) VALUES (N'MA008', N'Canh hoa tiêu Tứ Xuyên', N'/hinhAnh/canhCaHoaTieuTuXuyen.jpg', 285000, 1, N'LM003', NULL);
INSERT INTO monan (maMonAn, tenMonAn, urlhinhAnh, gia, trangThai, maLoaiMon,maKM) VALUES (N'MA009', N'Bánh bao ca dé', N'/hinhAnh/banhBaoCaDe.jpg', 115000, 1, N'LM001', NULL);
INSERT INTO monan (maMonAn, tenMonAn, urlhinhAnh, gia, trangThai, maLoaiMon,maKM) VALUES (N'MA010', N'Cảo bò bấm Truffle', N'/hinhAnh/caoBoNamTruffle.jpg', 155000, 1, N'LM001', NULL);
INSERT INTO monan (maMonAn, tenMonAn, urlhinhAnh, gia, trangThai, maLoaiMon,maKM) VALUES (N'MA011', N'Cuộn tôm chiên', N'/hinhAnh/cuonTomChien.jpg', 135000, 1, N'LM001', NULL);
INSERT INTO monan (maMonAn, tenMonAn, urlhinhAnh, gia, trangThai, maLoaiMon,maKM) VALUES (N'MA012', N'Chân gà sốt', N'/hinhAnh/chanGaSot.jpg', 125000, 1, N'LM001', NULL);
INSERT INTO monan (maMonAn, tenMonAn, urlhinhAnh, gia, trangThai, maLoaiMon,maKM) VALUES (N'MA013', N'Cảo tôm phúc lục', N'/hinhAnh/caoTomPhucLuc.jpg', 125000, 1, N'LM001', NULL);
INSERT INTO monan (maMonAn, tenMonAn, urlhinhAnh, gia, trangThai, maLoaiMon,maKM) VALUES (N'MA014', N'Ngọc bích bó xôi', N'/hinhAnh/ngocBichBoXoi.jpg', 130000, 1, N'LM001', NULL);
INSERT INTO monan (maMonAn, tenMonAn, urlhinhAnh, gia, trangThai, maLoaiMon,maKM) VALUES (N'MA015', N'Tôm chiên sốt táo', N'/hinhAnh/tomChienSotTao.jpg', 135000, 1, N'LM001', NULL);
INSERT INTO monan (maMonAn, tenMonAn, urlhinhAnh, gia, trangThai, maLoaiMon,maKM) VALUES (N'MA016', N'Há cảo sò điệp', N'/hinhAnh/haCaoSoDiep.jpg', 145000, 1, N'LM001', NULL);
INSERT INTO monan (maMonAn, tenMonAn, urlhinhAnh, gia, trangThai, maLoaiMon,maKM) VALUES (N'MA017', N'Há cảo tôm', N'/hinhAnh/haCaoTom.jpg', 130000, 1, N'LM001', NULL);
INSERT INTO monan (maMonAn, tenMonAn, urlhinhAnh, gia, trangThai, maLoaiMon,maKM) VALUES (N'MA018', N'Xíu mại sò điệp', N'/hinhAnh/xiuMaiSoDiep.jpg', 125000, 1, N'LM001', NULL);
INSERT INTO monan (maMonAn, tenMonAn, urlhinhAnh, gia, trangThai, maLoaiMon,maKM) VALUES (N'MA019', N'Atiso mật ong hạt sen', N'/hinhAnh/atisoMatOngSenVang.jpg', 120000, 1, N'LM002', NULL);
INSERT INTO monan (maMonAn, tenMonAn, urlhinhAnh, gia, trangThai, maLoaiMon,maKM) VALUES (N'MA020', N'Trà trái cây', N'/hinhAnh/traTraiCay.jpg', 110000, 1, N'LM002', NULL);
INSERT INTO monan (maMonAn, tenMonAn, urlhinhAnh, gia, trangThai, maLoaiMon,maKM) VALUES (N'MA021', N'Heineken', N'/hinhAnh/heineken.jpg', 95000, 1, N'LM002', NULL);
INSERT INTO monan (maMonAn, tenMonAn, urlhinhAnh, gia, trangThai, maLoaiMon,maKM) VALUES (N'MA022', N'Trà đào cam sả', N'/hinhAnh/traDaoCamSa.jpg', 110000, 1, N'LM002', NULL);
INSERT INTO monan (maMonAn, tenMonAn, urlhinhAnh, gia, trangThai, maLoaiMon,maKM) VALUES (N'MA023', N'Hồng trà xí muội', N'/hinhAnh/hongTraXiMuoi.jpg', 110000, 1, N'LM002', NULL);
INSERT INTO monan (maMonAn, tenMonAn, urlhinhAnh, gia, trangThai, maLoaiMon,maKM) VALUES (N'MA024', N'Hồng trà quýt', N'/hinhAnh/hongTraQuyt.jpg', 115000, 1, N'LM002', NULL);
INSERT INTO monan (maMonAn, tenMonAn, urlhinhAnh, gia, trangThai, maLoaiMon,maKM) VALUES (N'MA025', N'Vịt quay bắc kinh', N'/hinhAnh/vitQuayBacKinh.jpg', 255000, 1, N'LM004', NULL);
INSERT INTO monan (maMonAn, tenMonAn, urlhinhAnh, gia, trangThai, maLoaiMon,maKM) VALUES (N'MA026', N'Vịt răng muối tỏi', N'/hinhAnh/vitRangMuoiToi.jpg', 275000, 1, N'LM004', NULL);
INSERT INTO monan (maMonAn, tenMonAn, urlhinhAnh, gia, trangThai, maLoaiMon,maKM) VALUES (N'MA027', N'Da vịt cuốn', N'/hinhAnh/daVitCuon.jpg', 155000, 1, N'LM004', NULL);
INSERT INTO monan (maMonAn, tenMonAn, urlhinhAnh, gia, trangThai, maLoaiMon,maKM) VALUES (N'MA028', N'Heo quay da giòn', N'/hinhAnh/heoQuayDaGion.jpeg', 205000, 1, N'LM005', NULL);
INSERT INTO monan (maMonAn, tenMonAn, urlhinhAnh, gia, trangThai, maLoaiMon,maKM) VALUES (N'MA029', N'Sườn heo hầm', N'/hinhAnh/suonHeoHam.jpeg', 185000, 1, N'LM005', NULL);
INSERT INTO monan (maMonAn, tenMonAn, urlhinhAnh, gia, trangThai, maLoaiMon,maKM) VALUES (N'MA030', N'Thịt chua ngọt', N'/hinhAnh/thitChuaNgot.jpeg', 165000, 1, N'LM005', NULL);
INSERT INTO monan (maMonAn, tenMonAn, urlhinhAnh, gia, trangThai, maLoaiMon,maKM) VALUES (N'MA031', N'Bào ngư sốt hồng kông', N'/hinhAnh/baoNguSotHongKong.jpeg', 245000, 1, N'LM006', NULL);
INSERT INTO monan (maMonAn, tenMonAn, urlhinhAnh, gia, trangThai, maLoaiMon,maKM) VALUES (N'MA032', N'Sò điệp cháy tỏi', N'/hinhAnh/soDiepChayToi.jpeg', 245000, 1, N'LM006', NULL);
INSERT INTO monan (maMonAn, tenMonAn, urlhinhAnh, gia, trangThai, maLoaiMon,maKM) VALUES (N'MA033', N'Cua sốt hoàng kim', N'/hinhAnh/cuaSotHoangKim.jpeg', 245000, 1, N'LM006', NULL);
INSERT INTO monan (maMonAn, tenMonAn, urlhinhAnh, gia, trangThai, maLoaiMon,maKM) VALUES (N'MA034', N'Tàu hủ nước đường', N'/hinhAnh/tauHuNuocDuong.jpeg', 105000, 1, N'LM007', NULL);
INSERT INTO monan (maMonAn, tenMonAn, urlhinhAnh, gia, trangThai, maLoaiMon,maKM) VALUES (N'MA035', N'Cơm lươn sốt tiêu đên', N'/hinhAnh/comLuonSotTieuDen.jpeg', 1155000, 1, N'LM008', NULL);
INSERT INTO monan (maMonAn, tenMonAn, urlhinhAnh, gia, trangThai, maLoaiMon,maKM) VALUES (N'MA036', N'Cơm gà xì dầu', N'/hinhAnh/comGaXiDau.jpeg', 165000, 1, N'LM008', NULL);
INSERT INTO monan (maMonAn, tenMonAn, urlhinhAnh, gia, trangThai, maLoaiMon,maKM) VALUES (N'MA037', N'Cơm tôm ngọc bích', N'/hinhAnh/comTomNgocBich.jpeg', 165000, 1, N'LM008', NULL);
INSERT INTO monan (maMonAn, tenMonAn, urlhinhAnh, gia, trangThai, maLoaiMon,maKM) VALUES (N'MA038', N'Bông cải xanh sốt dầu hào', N'/hinhAnh/bongCaiXanhSotDauHao.jpeg', 145000, 1, N'LM010', NULL);
INSERT INTO monan (maMonAn, tenMonAn, urlhinhAnh, gia, trangThai, maLoaiMon,maKM) VALUES (N'MA039', N'Cải hongkong xào tỏi', N'/hinhAnh/caiHongKongXaoToi.jpeg', 135000, 1, N'LM010', NULL);
INSERT INTO monan (maMonAn, tenMonAn, urlhinhAnh, gia, trangThai, maLoaiMon,maKM) VALUES (N'MA040', N'Đậu que xào quế lân', N'/hinhAnh/dauQueXaoQueLan.jpeg', 140000, 1, N'LM010', NULL);
INSERT INTO monan (maMonAn, tenMonAn, urlhinhAnh, gia, trangThai, maLoaiMon,maKM) VALUES (N'MA041', N'Chè dương chi kim lộ', N'/hinhAnh/cheDuongChiKimLo.jpeg', 123000, 1, N'LM011', NULL);
INSERT INTO monan (maMonAn, tenMonAn, urlhinhAnh, gia, trangThai, maLoaiMon,maKM) VALUES (N'MA042', N'Chè củ năng hạt sen', N'/hinhAnh/cheCuNangHatSen.jpeg', 123000, 1, N'LM011', NULL);
INSERT INTO monan (maMonAn, tenMonAn, urlhinhAnh, gia, trangThai, maLoaiMon,maKM) VALUES (N'MA043', N'Chè sen nhãnn', N'/hinhAnh/cheSenNhan.jpeg', 123000, 0, N'LM011', NULL);
INSERT INTO monan (maMonAn, tenMonAn, urlhinhAnh, gia, trangThai, maLoaiMon,maKM) VALUES (N'MA046', N'Bào Ngư', N'/hinhAnh/baoNguSotHongKong.jpeg', 120000, 1, N'LM001', NULL);
INSERT INTO monan (maMonAn, tenMonAn, urlhinhAnh, gia, trangThai, maLoaiMon,maKM) VALUES (N'MA048', N'Nước', N'/hinhAnh/atisoMatOngSenVang.jpg', 120000, 1, N'LM002', NULL);



INSERT loaikhachhang (maLoaiKH, tenLoaiKH, giamGiaTV, giamGiaSN) VALUES (N'LKH1', N'Thành viên', 0, 0.05);
INSERT loaikhachhang (maLoaiKH, tenLoaiKH, giamGiaTV, giamGiaSN) VALUES (N'LKH2', N'Vàng', 0.1, 0.1);
INSERT loaikhachhang (maLoaiKH, tenLoaiKH, giamGiaTV, giamGiaSN) VALUES (N'LKH3', N'Kim Cương', 0.15, 0.15);


INSERT khachhang (maKH, tenKH, sdt, email, diemTL, trangThai, maLoaiKH, ngaySinh, ngayTao) VALUES (N'KH000001', N'Trần Thị Bích', N'0912345678', N'bichtran@gmail.com', 0, 1, N'LKH1', CAST(N'1992-02-12' AS Date), CAST(N'2024-12-09' AS Date));
INSERT khachhang (maKH, tenKH, sdt, email, diemTL, trangThai, maLoaiKH, ngaySinh, ngayTao)  VALUES (N'KH000002', N'Lê Hoàng Phúc', N'0923456789', N'phucle@gmail.com', 281, 1, N'LKH2', CAST(N'1994-12-13' AS Date), CAST(N'2024-12-09' AS Date));
INSERT khachhang (maKH, tenKH, sdt, email, diemTL, trangThai, maLoaiKH, ngaySinh, ngayTao)  VALUES (N'KH000003', N'Nguyễn Xuân Mạnh', N'0367155132', N'hthanhtuan.2307@gmail.com', 525, 1, N'LKH3', CAST(N'1994-07-14' AS Date), CAST(N'2024-12-13' AS Date));

insert  loaikhuyenmai(maLoaiKM, tenLoaiKM) values (N'LKM01',N'Hóa Đơn');
insert  loaikhuyenmai(maLoaiKM, tenLoaiKM) values (N'LKM02',N'Món Ăn');

INSERT khuyenmai (maKM, tenKM, chietKhau, ngayKT, ngayBD, soLuong, maLoaiKM) VALUES (N'KM001', N'Voucher Mùa Đông Ấm Áp', 10, CAST(N'2024-12-30' AS Date), CAST(N'2024-09-01' AS Date), 26, N'LKM01');
INSERT khuyenmai (maKM, tenKM, chietKhau, ngayKT, ngayBD, soLuong, maLoaiKM) VALUES (N'KM002', N'Ưu đãi cuối tháng 8', 10, CAST(N'2024-12-12' AS Date), CAST(N'2024-09-01' AS Date), 41, N'LKM01');
INSERT khuyenmai (maKM, tenKM, chietKhau, ngayKT, ngayBD, soLuong, maLoaiKM) VALUES (N'KM003', N'Ưu đãi 30 tháng 4', 20, CAST(N'2024-05-01' AS Date), CAST(N'2024-04-25' AS Date), 70, N'LKM01');
INSERT khuyenmai (maKM, tenKM, chietKhau, ngayKT, ngayBD, soLuong, maLoaiKM) VALUES (N'KM004', N'Ưu đãi giữa tháng 1', 50, CAST(N'2024-01-20' AS Date), CAST(N'2024-01-10' AS Date), 1, N'LKM02');
INSERT khuyenmai (maKM, tenKM, chietKhau, ngayKT, ngayBD, soLuong, maLoaiKM) VALUES (N'KM005', N'Ưu đãi cuối tháng 1', 40, CAST(N'2024-02-01' AS Date), CAST(N'2024-01-25' AS Date), 2, N'LKM02');
INSERT khuyenmai (maKM, tenKM, chietKhau, ngayKT, ngayBD, soLuong, maLoaiKM) VALUES (N'KM008', N'Mùa Đông Ấm Áp', 10, CAST(N'2024-12-30' AS Date), CAST(N'2024-12-13' AS Date), 8, N'LKM01');
INSERT khuyenmai (maKM, tenKM, chietKhau, ngayKT, ngayBD, soLuong, maLoaiKM) VALUES (N'KM010', N'Cứu Cảo Tôm Phúc Lục', 10, CAST(N'2024-12-31' AS Date), CAST(N'2024-12-13' AS Date), 0, N'LKM02');
INSERT khuyenmai (maKM, tenKM, chietKhau, ngayKT, ngayBD, soLuong, maLoaiKM) VALUES (N'KM011', N'Khuyến mãi tháng 11', 10, CAST(N'2024-11-14' AS Date), CAST(N'2024-11-01' AS Date), 100, N'LKM02');
INSERT khuyenmai (maKM, tenKM, chietKhau, ngayKT, ngayBD, soLuong, maLoaiKM) VALUES (N'KM012', N'Khuyến mãi đầu tháng 11', 10, CAST(N'2024-12-20' AS Date), CAST(N'2024-12-12' AS Date), 19, N'LKM02');





INSERT nhanvien (maNV, tenNV,sdt, matKhau, trangThai, ngaySinh, gioiTinh, maXacThuc, email,loaiNV) VALUES (N'AD001', N'Nguyễn Tâm Thành',  N'0938573845', N'ee79976c9380d5e337fc1c095ece8c8f22f91f306ceeb161fa51fecede2c4ba1', 1, CAST(N'1990-10-12' AS Date), 0, NULL, N'tamthanhcmg@gmail.com','QL');
INSERT nhanvien (maNV, tenNV,sdt, matKhau, trangThai, ngaySinh, gioiTinh, maXacThuc, email,loaiNV)  VALUES (N'NVLT002', N'Khồng Văn Tám' ,N'037624338', N'ee79976c9380d5e337fc1c095ece8c8f22f91f306ceeb161fa51fecede2c4ba1', 1,  CAST(N'2001-09-12' AS Date), 0, NULL, N'khongtam88888888@gmail.com','NV');
INSERT nhanvien (maNV, tenNV,sdt, matKhau, trangThai, ngaySinh, gioiTinh, maXacThuc, email,loaiNV) VALUES (N'NVQL001', N'Phạm Thành Trí',  N'0387512346', N'ee79976c9380d5e337fc1c095ece8c8f22f91f306ceeb161fa51fecede2c4ba1', 1, CAST(N'1996-07-31' AS Date), 0, NULL, N'phamthanhtri0712@gmail.com','NV');
INSERT nhanvien (maNV, tenNV,sdt, matKhau, trangThai, ngaySinh, gioiTinh, maXacThuc, email,loaiNV) VALUES (N'NVTN002', N'Hà Thanh Tuấn',  N'0928594324', N'ee79976c9380d5e337fc1c095ece8c8f22f91f306ceeb161fa51fecede2c4ba1', 1,  CAST(N'2000-08-16' AS Date), 0, NULL, N'hthanhtuan.2307@gmail.com','NV');






INSERT dondatban (maDDB,  soLuongKH, tienCoc, trangThai, gioDat, tienHoanCoc, gioHuy, maNhanVien, ngayTao, ghiChu, maBan,maKhachHang) VALUES (N'DB241213001', 2, 100000, 0, CAST(N'2024-12-13T13:00:00.000' AS DateTime), 70000, CAST(N'2024-12-13T08:52:25.680' AS DateTime), N'AD001', CAST(N'2024-12-13' AS Date), NULL, N'T1001',N'KH000001');
INSERT dondatban (maDDB,  soLuongKH, tienCoc, trangThai, gioDat, tienHoanCoc, gioHuy, maNhanVien, ngayTao, ghiChu, maBan,maKhachHang) VALUES (N'DB241213002',  8, 399400, 0, CAST(N'2024-12-13T12:00:00.000' AS DateTime), 279580, CAST(N'2024-12-13T11:55:14.970' AS DateTime), N'AD001', CAST(N'2024-12-13' AS Date), NULL, N'T2001',N'KH000002');
INSERT dondatban (maDDB,  soLuongKH, tienCoc, trangThai, gioDat, tienHoanCoc, gioHuy, maNhanVien, ngayTao, ghiChu, maBan,maKhachHang) VALUES (N'DB241213003',  12, 502900, 1, CAST(N'2024-12-13T10:00:00.000' AS DateTime), 0, NULL, N'AD001', CAST(N'2024-12-13' AS Date), NULL, N'T3001',N'KH000001');
INSERT dondatban (maDDB,  soLuongKH, tienCoc, trangThai, gioDat, tienHoanCoc, gioHuy, maNhanVien, ngayTao, ghiChu, maBan,maKhachHang) VALUES (N'DB241213004',  6, 422500, 1, CAST(N'2024-12-13T12:15:00.000' AS DateTime), 0, NULL, N'AD001', CAST(N'2024-12-13' AS Date), NULL, N'T2010',N'KH000003');
INSERT dondatban (maDDB,  soLuongKH, tienCoc, trangThai, gioDat, tienHoanCoc, gioHuy, maNhanVien, ngayTao, ghiChu, maBan,maKhachHang) VALUES (N'DB241213005', 12, 302500, 1, CAST(N'2024-12-13T14:00:00.000' AS DateTime), 0, NULL, N'AD001', CAST(N'2024-12-13' AS Date), NULL, N'T3001',N'KH000002');
INSERT dondatban (maDDB,  soLuongKH, tienCoc, trangThai, gioDat, tienHoanCoc, gioHuy, maNhanVien, ngayTao, ghiChu, maBan,maKhachHang) VALUES (N'DB241213006',  8, 100000, 1, CAST(N'2024-12-13T16:30:00.000' AS DateTime), 0, NULL, N'AD001', CAST(N'2024-12-13' AS Date), NULL, N'T2006',N'KH000001');
INSERT dondatban (maDDB,  soLuongKH, tienCoc, trangThai, gioDat, tienHoanCoc, gioHuy, maNhanVien, ngayTao, ghiChu, maBan,maKhachHang) VALUES (N'DB241213007', 2, 100000, 1, CAST(N'2024-12-13T17:00:00.000' AS DateTime), 0, NULL, N'AD001', CAST(N'2024-12-13' AS Date), NULL, N'T1010',N'KH000003');
INSERT dondatban (maDDB,  soLuongKH, tienCoc, trangThai, gioDat, tienHoanCoc, gioHuy, maNhanVien, ngayTao, ghiChu, maBan,maKhachHang) VALUES (N'DB241213008',  5, 664000, 1, CAST(N'2024-12-13T16:45:00.000' AS DateTime), 0, NULL, N'AD001', CAST(N'2024-12-13' AS Date), NULL, N'T2008',N'KH000002');
INSERT dondatban (maDDB,  soLuongKH, tienCoc, trangThai, gioDat, tienHoanCoc, gioHuy, maNhanVien, ngayTao, ghiChu, maBan,maKhachHang) VALUES (N'DB241213009',  2, 372400, 1, CAST(N'2024-12-13T17:15:00.000' AS DateTime), 0, NULL, N'AD001', CAST(N'2024-12-13' AS Date), NULL, N'T1009',N'KH000001');
INSERT dondatban (maDDB,  soLuongKH, tienCoc, trangThai, gioDat, tienHoanCoc, gioHuy, maNhanVien, ngayTao, ghiChu, maBan,maKhachHang) VALUES (N'DB241213010',  4, 376900, 1, CAST(N'2024-12-13T17:30:00.000' AS DateTime), 0, NULL, N'AD001', CAST(N'2024-12-13' AS Date), NULL, N'T1004',N'KH000003');









INSERT chitietdatban (maMonAn, maDonDatBan, soLuong, thanhTien, donGia) VALUES (N'MA001', N'DB241213009', 1, 105000, 105000);
INSERT chitietdatban (maMonAn, maDonDatBan, soLuong, thanhTien, donGia)VALUES (N'MA003', N'DB241213003', 1, 115000, 115000);
INSERT chitietdatban (maMonAn, maDonDatBan, soLuong, thanhTien, donGia)VALUES (N'MA003', N'DB241213008', 3, 345000, 115000);
INSERT chitietdatban (maMonAn, maDonDatBan, soLuong, thanhTien, donGia)VALUES (N'MA004', N'DB241213008', 1, 155000, 155000);
INSERT chitietdatban (maMonAn, maDonDatBan, soLuong, thanhTien, donGia)VALUES (N'MA006', N'DB241213002', 1, 175000, 175000);
INSERT chitietdatban (maMonAn, maDonDatBan, soLuong, thanhTien, donGia)VALUES (N'MA006', N'DB241213003', 1, 175000, 175000);
INSERT chitietdatban (maMonAn, maDonDatBan, soLuong, thanhTien, donGia)VALUES (N'MA006', N'DB241213010', 1, 175000, 175000);
INSERT chitietdatban (maMonAn, maDonDatBan, soLuong, thanhTien, donGia)VALUES (N'MA007', N'DB241213008', 1, 255000, 255000);
INSERT chitietdatban (maMonAn, maDonDatBan, soLuong, thanhTien, donGia)VALUES (N'MA008', N'DB241213008', 1, 285000, 285000);
INSERT chitietdatban (maMonAn, maDonDatBan, soLuong, thanhTien, donGia)VALUES (N'MA010', N'DB241213002', 1, 155000, 155000);
INSERT chitietdatban (maMonAn, maDonDatBan, soLuong, thanhTien, donGia)VALUES (N'MA010', N'DB241213004', 1, 155000, 155000);
INSERT chitietdatban (maMonAn, maDonDatBan, soLuong, thanhTien, donGia)VALUES (N'MA010', N'DB241213005', 1, 155000, 155000);
INSERT chitietdatban (maMonAn, maDonDatBan, soLuong, thanhTien, donGia)VALUES (N'MA011', N'DB241213009', 1, 135000, 135000);
INSERT chitietdatban (maMonAn, maDonDatBan, soLuong, thanhTien, donGia)VALUES (N'MA013', N'DB241213005', 1, 125000, 125000);
INSERT chitietdatban (maMonAn, maDonDatBan, soLuong, thanhTien, donGia)VALUES (N'MA014', N'DB241213005', 1, 130000, 130000);
INSERT chitietdatban (maMonAn, maDonDatBan, soLuong, thanhTien, donGia)VALUES (N'MA016', N'DB241213005', 1, 145000, 145000);
INSERT chitietdatban (maMonAn, maDonDatBan, soLuong, thanhTien, donGia)VALUES (N'MA020', N'DB241213003', 1, 110000, 110000);
INSERT chitietdatban (maMonAn, maDonDatBan, soLuong, thanhTien, donGia)VALUES (N'MA020', N'DB241213008', 1, 110000, 110000);
INSERT chitietdatban (maMonAn, maDonDatBan, soLuong, thanhTien, donGia)VALUES (N'MA020', N'DB241213010', 1, 110000, 110000);
INSERT chitietdatban (maMonAn, maDonDatBan, soLuong, thanhTien, donGia)VALUES (N'MA021', N'DB241213003', 2, 190000, 95000);
INSERT chitietdatban (maMonAn, maDonDatBan, soLuong, thanhTien, donGia)VALUES (N'MA021', N'DB241213008', 1, 95000, 95000);
INSERT chitietdatban (maMonAn, maDonDatBan, soLuong, thanhTien, donGia)VALUES (N'MA022', N'DB241213003', 2, 220000, 110000);
INSERT chitietdatban (maMonAn, maDonDatBan, soLuong, thanhTien, donGia)VALUES (N'MA025', N'DB241213008', 1, 255000, 255000);
INSERT chitietdatban (maMonAn, maDonDatBan, soLuong, thanhTien, donGia)VALUES (N'MA026', N'DB241213008', 1, 275000, 275000);
INSERT chitietdatban (maMonAn, maDonDatBan, soLuong, thanhTien, donGia)VALUES (N'MA030', N'DB241213004', 1, 165000, 165000);
INSERT chitietdatban (maMonAn, maDonDatBan, soLuong, thanhTien, donGia)VALUES (N'MA032', N'DB241213010', 1, 245000, 245000);
INSERT chitietdatban (maMonAn, maDonDatBan, soLuong, thanhTien, donGia)VALUES (N'MA033', N'DB241213002', 1, 245000, 245000);
INSERT chitietdatban (maMonAn, maDonDatBan, soLuong, thanhTien, donGia)VALUES (N'MA033', N'DB241213004', 1, 245000, 245000);
INSERT chitietdatban (maMonAn, maDonDatBan, soLuong, thanhTien, donGia)VALUES (N'MA033', N'DB241213009', 1, 245000, 245000);
INSERT chitietdatban (maMonAn, maDonDatBan, soLuong, thanhTien, donGia)VALUES (N'MA034', N'DB241213003', 1, 105000, 105000);
INSERT chitietdatban (maMonAn, maDonDatBan, soLuong, thanhTien, donGia)VALUES (N'MA034', N'DB241213004', 1, 105000, 105000);
INSERT chitietdatban (maMonAn, maDonDatBan, soLuong, thanhTien, donGia)VALUES (N'MA034', N'DB241213008', 1, 105000, 105000);
INSERT chitietdatban (maMonAn, maDonDatBan, soLuong, thanhTien, donGia)VALUES (N'MA034', N'DB241213010', 1, 105000, 105000);
INSERT chitietdatban (maMonAn, maDonDatBan, soLuong, thanhTien, donGia)VALUES (N'MA036', N'DB241213002', 1, 165000, 165000);
INSERT chitietdatban (maMonAn, maDonDatBan, soLuong, thanhTien, donGia)VALUES (N'MA036', N'DB241213003', 1, 165000, 165000);
INSERT chitietdatban (maMonAn, maDonDatBan, soLuong, thanhTien, donGia)VALUES (N'MA036', N'DB241213004', 1, 165000, 165000);
INSERT chitietdatban (maMonAn, maDonDatBan, soLuong, thanhTien, donGia)VALUES (N'MA036', N'DB241213010', 1, 165000, 165000);
INSERT chitietdatban (maMonAn, maDonDatBan, soLuong, thanhTien, donGia)VALUES (N'MA037', N'DB241213009', 1, 165000, 165000);
INSERT chitietdatban (maMonAn, maDonDatBan, soLuong, thanhTien, donGia)VALUES (N'MA039', N'DB241213002', 1, 135000, 135000);
INSERT chitietdatban (maMonAn, maDonDatBan, soLuong, thanhTien, donGia)VALUES (N'MA039', N'DB241213009', 1, 135000, 135000);
INSERT chitietdatban (maMonAn, maDonDatBan, soLuong, thanhTien, donGia)VALUES (N'MA040', N'DB241213003', 1, 140000, 140000);
INSERT chitietdatban (maMonAn, maDonDatBan, soLuong, thanhTien, donGia)VALUES (N'MA042', N'DB241213003', 1, 123000, 123000);
INSERT chitietdatban (maMonAn, maDonDatBan, soLuong, thanhTien, donGia)VALUES (N'MA043', N'DB241213002', 1, 123000, 123000);
INSERT chitietdatban (maMonAn, maDonDatBan, soLuong, thanhTien, donGia)VALUES (N'MA043', N'DB241213009', 1, 123000, 123000);
INSERT chitietdatban (maMonAn, maDonDatBan, soLuong, thanhTien, donGia)VALUES (N'MA043', N'DB241213010', 1, 123000, 123000);
INSERT chitietdatban (maMonAn, maDonDatBan, soLuong, thanhTien, donGia)VALUES (N'MA046', N'DB241213005', 1, 120000, 120000);
INSERT chitietdatban (maMonAn, maDonDatBan, soLuong, thanhTien, donGia)VALUES (N'MA048', N'DB241213004', 2, 240000, 120000);


INSERT hoadon (maHD, ngayTao, tongTien, tongTienThanhToan, trangThai, maKhachHang, maNhanVien, maDonDatBan, maKhuyenMai, maBan, tongTienGiamGia, gioVao, gioRa) VALUES (N'HD241212001', CAST(N'2024-12-12' AS Date), 420000, 474600, 1, N'KH000001', N'AD001', N'DB241213005', NULL, N'T1001', 0, CAST(N'2024-12-12T18:46:27.370' AS DateTime), CAST(N'2024-12-12T18:53:20.993' AS DateTime));
INSERT hoadon (maHD, ngayTao, tongTien, tongTienThanhToan, trangThai, maKhachHang, maNhanVien, maDonDatBan, maKhuyenMai, maBan, tongTienGiamGia, gioVao, gioRa) VALUES (N'HD241212002', CAST(N'2024-12-12' AS Date), 420000, 474600, 1,N'KH000001', N'AD001', N'DB241213010', NULL, N'T1001', 0,  CAST(N'2024-12-12T18:53:56.530' AS DateTime), CAST(N'2024-12-12T18:54:05.727' AS DateTime));
INSERT hoadon (maHD, ngayTao, tongTien, tongTienThanhToan, trangThai, maKhachHang, maNhanVien, maDonDatBan, maKhuyenMai, maBan, tongTienGiamGia, gioVao, gioRa) VALUES (N'HD241212003', CAST(N'2024-12-12' AS Date), 290000, 327700, 1, N'KH000001', N'AD001', N'DB241213006', NULL, N'T1001', 0, CAST(N'2024-12-12T18:57:18.153' AS DateTime), CAST(N'2024-12-12T19:08:32.000' AS DateTime));
INSERT hoadon (maHD, ngayTao, tongTien, tongTienThanhToan, trangThai, maKhachHang, maNhanVien, maDonDatBan, maKhuyenMai, maBan, tongTienGiamGia, gioVao, gioRa) VALUES (N'HD241213001', CAST(N'2024-12-13' AS Date), 1343000, 1114690, 1, N'KH000001', N'AD001', N'DB241213003', NULL, N'T3001', 0,  CAST(N'2024-12-13T09:41:56.127' AS DateTime), CAST(N'2024-12-13T12:00:35.350' AS DateTime));
INSERT hoadon (maHD, ngayTao, tongTien, tongTienThanhToan, trangThai, maKhachHang, maNhanVien, maDonDatBan, maKhuyenMai, maBan, tongTienGiamGia, gioVao, gioRa) VALUES (N'HD241213002', CAST(N'2024-12-13' AS Date), 1320000, 1069100, 1, N'KH000002', N'AD001', N'DB241213004', NULL, N'T2010', 0, CAST(N'2024-12-13T12:00:50.767' AS DateTime), CAST(N'2024-12-13T12:01:17.303' AS DateTime));
INSERT hoadon (maHD, ngayTao, tongTien, tongTienThanhToan, trangThai, maKhachHang, maNhanVien, maDonDatBan, maKhuyenMai, maBan, tongTienGiamGia, gioVao, gioRa) VALUES (N'HD241213003', CAST(N'2024-12-13' AS Date), 1293000, 999990, 1, N'KH000002', N'AD001', N'DB241213002', N'KM008', N'T3001', 129300,  CAST(N'2024-12-13T13:44:45.440' AS DateTime), CAST(N'2024-12-13T13:47:26.550' AS DateTime));
INSERT hoadon (maHD, ngayTao, tongTien, tongTienThanhToan, trangThai, maKhachHang, maNhanVien, maDonDatBan, maKhuyenMai, maBan, tongTienGiamGia, gioVao, gioRa) VALUES (N'HD241213004', CAST(N'2024-12-13' AS Date), 1845000, 1715850, 1, N'KH000002', N'AD001', N'DB241213007', N'KM008', N'T1002', 184500,  CAST(N'2024-12-13T13:51:59.430' AS DateTime), CAST(N'2024-12-13T13:54:06.587' AS DateTime));
INSERT hoadon (maHD, ngayTao, tongTien, tongTienThanhToan, trangThai, maKhachHang, maNhanVien, maDonDatBan, maKhuyenMai, maBan, tongTienGiamGia, gioVao, gioRa) VALUES (N'HD241213005', CAST(N'2024-12-13' AS Date), 290000, 284200, 1, N'KH000003', N'AD001', N'DB241213008', NULL, N'T1001', 43500, CAST(N'2024-12-13T13:55:43.297' AS DateTime), CAST(N'2024-12-13T14:17:42.213' AS DateTime));





INSERT chitiethoadon (maHoaDon, maMonAn, donGia, soLuong, thanhTien) VALUES (N'HD241212001', N'MA010', 155000, 1, 155000);

INSERT chitiethoadon (maHoaDon, maMonAn, donGia, soLuong, thanhTien) VALUES (N'HD241212001', N'MA011', 135000, 1, 135000);

INSERT chitiethoadon (maHoaDon, maMonAn, donGia, soLuong, thanhTien) VALUES (N'HD241212001', N'MA014', 130000, 1, 130000);

INSERT chitiethoadon (maHoaDon, maMonAn, donGia, soLuong, thanhTien) VALUES (N'HD241212002', N'MA010', 155000, 1, 155000);

INSERT chitiethoadon (maHoaDon, maMonAn, donGia, soLuong, thanhTien) VALUES (N'HD241212002', N'MA011', 135000, 1, 135000);

INSERT chitiethoadon (maHoaDon, maMonAn, donGia, soLuong, thanhTien) VALUES (N'HD241212002', N'MA014', 130000, 1, 130000);

INSERT chitiethoadon (maHoaDon, maMonAn, donGia, soLuong, thanhTien) VALUES (N'HD241212003', N'MA010', 155000, 1, 155000);

INSERT chitiethoadon (maHoaDon, maMonAn, donGia, soLuong, thanhTien) VALUES (N'HD241212003', N'MA011', 135000, 1, 135000);

INSERT chitiethoadon (maHoaDon, maMonAn, donGia, soLuong, thanhTien) VALUES (N'HD241213001', N'MA003', 115000, 1, 115000);

INSERT chitiethoadon (maHoaDon, maMonAn, donGia, soLuong, thanhTien) VALUES (N'HD241213001', N'MA006', 175000, 1, 175000);

INSERT chitiethoadon (maHoaDon, maMonAn, donGia, soLuong, thanhTien) VALUES (N'HD241213001', N'MA020', 110000, 1, 110000);

INSERT chitiethoadon (maHoaDon, maMonAn, donGia, soLuong, thanhTien) VALUES (N'HD241213001', N'MA021', 190000, 2, 95000);

INSERT chitiethoadon (maHoaDon, maMonAn, donGia, soLuong, thanhTien) VALUES (N'HD241213001', N'MA022', 220000, 2, 110000);

INSERT chitiethoadon (maHoaDon, maMonAn, donGia, soLuong, thanhTien) VALUES (N'HD241213001', N'MA034', 105000, 1, 105000);

INSERT chitiethoadon (maHoaDon, maMonAn, donGia, soLuong, thanhTien) VALUES (N'HD241213001', N'MA036', 165000, 1, 165000);

INSERT chitiethoadon (maHoaDon, maMonAn, donGia, soLuong, thanhTien) VALUES (N'HD241213001', N'MA040', 140000, 1, 140000);

INSERT chitiethoadon (maHoaDon, maMonAn, donGia, soLuong, thanhTien) VALUES (N'HD241213001', N'MA042', 123000, 1, 123000);

INSERT chitiethoadon (maHoaDon, maMonAn, donGia, soLuong, thanhTien) VALUES (N'HD241213002', N'MA009', 115000, 1, 115000);

INSERT chitiethoadon (maHoaDon, maMonAn, donGia, soLuong, thanhTien) VALUES (N'HD241213002', N'MA010', 155000, 1, 155000);

INSERT chitiethoadon (maHoaDon, maMonAn, donGia, soLuong, thanhTien) VALUES (N'HD241213002', N'MA014', 130000, 1, 130000);

INSERT chitiethoadon (maHoaDon, maMonAn, donGia, soLuong, thanhTien) VALUES (N'HD241213002', N'MA030', 165000, 1, 165000);

INSERT chitiethoadon (maHoaDon, maMonAn, donGia, soLuong, thanhTien) VALUES (N'HD241213002', N'MA033', 245000, 1, 245000);

INSERT chitiethoadon (maHoaDon, maMonAn, donGia, soLuong, thanhTien) VALUES (N'HD241213002', N'MA034', 105000, 1, 105000);

INSERT chitiethoadon (maHoaDon, maMonAn, donGia, soLuong, thanhTien) VALUES (N'HD241213002', N'MA036', 165000, 1, 165000);

INSERT chitiethoadon (maHoaDon, maMonAn, donGia, soLuong, thanhTien) VALUES (N'HD241213002', N'MA048', 240000, 2, 120000);

INSERT chitiethoadon (maHoaDon, maMonAn, donGia, soLuong, thanhTien) VALUES (N'HD241213003', N'MA010', 155000, 1, 155000);

INSERT chitiethoadon (maHoaDon, maMonAn, donGia, soLuong, thanhTien) VALUES (N'HD241213003', N'MA013', 125000, 1, 125000);

INSERT chitiethoadon (maHoaDon, maMonAn, donGia, soLuong, thanhTien) VALUES (N'HD241213003', N'MA014', 130000, 1, 130000);

INSERT chitiethoadon (maHoaDon, maMonAn, donGia, soLuong, thanhTien) VALUES (N'HD241213003', N'MA016', 145000, 1, 145000);

INSERT chitiethoadon (maHoaDon, maMonAn, donGia, soLuong, thanhTien) VALUES (N'HD241213003', N'MA036', 165000, 1, 165000);

INSERT chitiethoadon (maHoaDon, maMonAn, donGia, soLuong, thanhTien) VALUES (N'HD241213003', N'MA037', 330000, 2, 165000);

INSERT chitiethoadon (maHoaDon, maMonAn, donGia, soLuong, thanhTien) VALUES (N'HD241213003', N'MA043', 123000, 1, 123000);

INSERT chitiethoadon (maHoaDon, maMonAn, donGia, soLuong, thanhTien) VALUES (N'HD241213003', N'MA046', 120000, 1, 120000);

INSERT chitiethoadon (maHoaDon, maMonAn, donGia, soLuong, thanhTien) VALUES (N'HD241213004', N'MA003', 115000, 1, 115000);

INSERT chitiethoadon (maHoaDon, maMonAn, donGia, soLuong, thanhTien) VALUES (N'HD241213004', N'MA011', 135000, 1, 135000);

INSERT chitiethoadon (maHoaDon, maMonAn, donGia, soLuong, thanhTien) VALUES (N'HD241213004', N'MA014', 130000, 1, 130000);

INSERT chitiethoadon (maHoaDon, maMonAn, donGia, soLuong, thanhTien) VALUES (N'HD241213004', N'MA026', 825000, 3, 275000);

INSERT chitiethoadon (maHoaDon, maMonAn, donGia, soLuong, thanhTien) VALUES (N'HD241213004', N'MA036', 495000, 3, 165000);

INSERT chitiethoadon (maHoaDon, maMonAn, donGia, soLuong, thanhTien) VALUES (N'HD241213004', N'MA038', 145000, 1, 145000);

INSERT chitiethoadon (maHoaDon, maMonAn, donGia, soLuong, thanhTien) VALUES (N'HD241213005', N'MA010', 155000, 1, 155000);

INSERT chitiethoadon (maHoaDon, maMonAn, donGia, soLuong, thanhTien) VALUES (N'HD241213005', N'MA011', 135000, 1, 135000);

